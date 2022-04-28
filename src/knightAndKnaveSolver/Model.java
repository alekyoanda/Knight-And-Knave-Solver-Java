package knightAndKnaveSolver;

import knightAndKnaveSolver.logicalOperator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Model {
    public static HashMap<String, Integer> logicalOperatorWeight = new HashMap<>(){{
        put("<=>", 5);
        put("=>", 4);
        put("||", 3);
        put("&&", 2);
        put("'", 1);
    }};

    public static HashMap<String, Boolean> solveModel(HashMap<String, ArrayList<String>> sentencesDict){
        ArrayList<Object> knowledges = new ArrayList<>();
        int speakerIndex = 0;
        for (String speaker: sentencesDict.keySet()){
            for (int i=0; i<sentencesDict.get(speaker).size(); i++){
                if (isValidInput(sentencesDict.get(speaker).get(i))){
                    knowledges.add(makeBiimplication(makeModel(sentencesDict.get(speaker).get(i)), speakerIndex));
                }
                else return null;
            }
            speakerIndex++;
        }
        LogicalOperator finalKnowledge = unionKnowledge(knowledges);

        return checkAll(sentencesDict.size(), finalKnowledge);
    }

    public static Object makeModel(String premises){
        // Base case
        if (premises.length() == 1 && Character.isAlphabetic(premises.charAt(0))){
            return (int) premises.toUpperCase().charAt(0) - 65;
        }
        // Recursive case
        else{
            String slicedSentence = "";
            int i = 0;

            while (i != premises.length()){
                if (!premises.substring(i, i+1).equals("(")){
                    slicedSentence += premises.substring(i, i+1);
                    i++;
                }
                else {
                    while (!premises.substring(i, i+1).equals(")")){
                        slicedSentence += "*";
                        i++;
                    }
                }
            }
            boolean isParentheses = false;

            String slicedSentenceCheck = "";
            for (int j=0; j<slicedSentence.length() - 1; j++){
                slicedSentenceCheck += "*";
            }
            slicedSentenceCheck += ")";
            // If all the premises inside the parentheses, turn into True the isParentheses variable
            if (slicedSentence.equals(slicedSentenceCheck)){
                slicedSentence = premises;
                isParentheses = true;
            }

            int maxSymbol = 0;
            String currSymbol = "";

            for (String symbol: logicalOperatorWeight.keySet()){
                if (slicedSentence.contains(symbol)){
                    if (logicalOperatorWeight.get(symbol) > maxSymbol){
                        maxSymbol = logicalOperatorWeight.get(symbol);
                        currSymbol = symbol;
                    }
                }
            }

            if (isParentheses && currSymbol.equals("")){
                for (int j=0; j<premises.length(); j++){
                    if (Character.isAlphabetic(premises.charAt(j))){
                        return makeModel(premises.substring(i, i+1));
                    }
                }
            }

            int indexOfSymbol = slicedSentence.indexOf(currSymbol);
            LogicalOperator knowledge = null;
            Object leftSide = null;
            Object rightSide = null;

            if (currSymbol.equals("'")){
                leftSide = makeModel(premises.substring(0, indexOfSymbol));
            }
            else if (isParentheses){
                leftSide = makeModel(premises.substring(1, indexOfSymbol));
                rightSide = makeModel(premises.substring(indexOfSymbol + currSymbol.length(), premises.length()-1));
            }
            else {
                leftSide = makeModel(premises.substring(0, indexOfSymbol));
                rightSide = makeModel(premises.substring(indexOfSymbol + currSymbol.length()));
            }

            if (currSymbol.equals("<=>")) knowledge = new Biimplication(leftSide, rightSide);
            else if (currSymbol.equals("=>")) knowledge = new Implication(leftSide, rightSide);
            else if (currSymbol.equals("&&")) knowledge = new And(leftSide, rightSide);
            else if (currSymbol.equals("||")) knowledge = new Or(leftSide, rightSide);
            else if (currSymbol.equals("'")) knowledge = new Not(leftSide);

            return knowledge;
        }
    }

    private static LogicalOperator makeBiimplication(Object knowledge, int speakerIndex){
        return new Biimplication(speakerIndex, knowledge);
    }

    private static LogicalOperator unionKnowledge(ArrayList<Object> knowledges){
        And newKnowledge = new And();
        for (Object knowledge: knowledges){
            newKnowledge.addKnowledge(knowledge);
        }
        return newKnowledge;
    }

    private static ArrayList<Boolean> decimalToBinaryBoolean(int decimalNumber, int lengthBit){
        ArrayList<Boolean> boolList = new ArrayList<>();
        int counter = 0;

        while (counter != lengthBit){
            Boolean boolValue;
            if (decimalNumber % 2 == 0){
                boolValue = false;
            }
            else {
                boolValue = true;
            }
            boolList.add(boolValue);
            decimalNumber = decimalNumber / 2;
            counter++;
        }
        for (int i=0; i<boolList.size()/2; i++){
            Boolean tmp = boolList.get(i);
            boolList.set(i, boolList.get(boolList.size()-i-1));
            boolList.set(boolList.size()-i-1, tmp);
        }

        return boolList;
    }

    private static HashMap<String, Boolean> checkAll(int amountOfPeople, LogicalOperator finalKnowledge){
        ArrayList<Boolean> premisesTruth = new ArrayList<>();
        HashMap<String, Boolean> solution = new HashMap<>();
        int counter = 0;

        for (int i=0; i<Math.pow(2, amountOfPeople); i++){
            premisesTruth = decimalToBinaryBoolean(i, amountOfPeople);
            if (finalKnowledge.evaluate(premisesTruth)){
                counter++;
                for (int j=0; j<premisesTruth.size(); j++){
                    solution.put(Character.toString((char) j+65), premisesTruth.get(j));
                }
                break;
            }
        }
        if (counter == 1){
            return solution;
        }
        return null;
    }

    private static boolean isValidInput(String premises){
        ArrayList<String> characterAllowed = new ArrayList<>(
                Arrays.asList("(", ")", "<=>", "=>", "'", "&&", "||")
        );
        int counter = 0;
        int amountOfParentheses = 0;
        int i = 0;
        boolean alphaBefore = false;

        while (i != premises.length()){
            if (!Character.isAlphabetic(premises.charAt(i))){
                boolean characterExist = false;
                for (String chr: characterAllowed){
                    if (!(i + chr.length() > premises.length()) && chr.equals(premises.substring(i, i+chr.length()))){
                        i += chr.length() - 1;
                        characterExist = true;
                        break;
                    }
                }
                if (!characterExist) return false;
                alphaBefore = false;
            }
            else {
                if (alphaBefore) return false;
                alphaBefore = true;
                counter++;
            }

            if (premises.substring(i, i+1).equals("(")) amountOfParentheses += 1;
            else if (premises.substring(i, i+1).equals(")")) amountOfParentheses += 1;
            i++;
        }
        return (counter >= 0 && amountOfParentheses % 2 == 0);
    }

}
