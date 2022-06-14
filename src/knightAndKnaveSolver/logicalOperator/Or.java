package knightAndKnaveSolver.logicalOperator;

import java.util.ArrayList;

public class Or implements LogicalOperator{
    private ArrayList<Object> sentences = new ArrayList<>();

    public Or(Object ... sentences){
        for (Object sentence: sentences){
            this.sentences.add(sentence);
        }
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
            if (bool){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Or{" +
                "sentences=" + sentences.toString() +
                '}';
    }
}