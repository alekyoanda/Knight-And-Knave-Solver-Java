package knightAndKnaveSolver.logicalOperator;

import java.util.ArrayList;

public class And implements LogicalOperator{
    private ArrayList<Object> sentences = new ArrayList<>();

    public And(Object... sentences){
        for (Object sentence: sentences){
            this.sentences.add(sentence);
        }
    }

    public void addKnowledge(Object knowledge){
        this.sentences.add(knowledge);
    }

    @Override
    public boolean evaluate(ArrayList<Boolean> model) {
        ArrayList<Boolean> truthValue = new ArrayList<>();
        for (Object sentence: this.sentences){
            if (sentence instanceof Integer){
                Integer index = (Integer) sentence;
                truthValue.add(model.get(index));
            }
            else {
                LogicalOperator logicPremise = (LogicalOperator) sentence;
                truthValue.add(logicPremise.evaluate(model));
            }
        }
        for (Boolean bool: truthValue){
            if (!bool){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "And{" +
                "sentences=" + sentences.toString() +
                '}';
    }
}