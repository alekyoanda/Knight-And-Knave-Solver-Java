package knightAndKnaveSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Model program = new Model();
        HashMap<String, ArrayList<String>> sentencesDict = new HashMap<>();

        System.out.print("Amount of people: ");
        int amountOfPeople = Integer.parseInt(input.nextLine());

        ArrayList<String> sentencesEachPerson;

        for (int i=0; i<amountOfPeople; i++){
            sentencesEachPerson = new ArrayList<>();
            System.out.printf("How many sentence that %s said: ", (char) (i + 65));
            int amountOfSentence = Integer.parseInt(input.nextLine());
            System.out.println();
            System.out.printf("Sentence that %s said: %n", (char) (i + 65));
            for (int j=0; j<amountOfSentence; j++){
                System.out.printf("Sentence %d: ", j+1);
                String sentence = input.nextLine();
                sentencesEachPerson.add(sentence);
            }
            System.out.println();
            sentencesDict.put(String.valueOf((char)(i+65)), sentencesEachPerson);
        }

        HashMap<String, Boolean> result = Model.solveModel(sentencesDict);

        if (result == null){
            System.out.println("No solution");
        }
        else{
            System.out.println(result);
        }

    }
}
