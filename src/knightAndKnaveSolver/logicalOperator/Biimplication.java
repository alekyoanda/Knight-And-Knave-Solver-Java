package knightAndKnaveSolver.logicalOperator;

import java.util.ArrayList;

public class Biimplication extends LogicalOperator{
    private Object left;
    private Object right;

    public Biimplication(Object antecedent, Object consequent){
        this.left = antecedent;
        this.right = consequent;
    }

    @Override
    public boolean evaluate(ArrayList<Boolean> model) {
        boolean leftTruth = false;
        boolean rightTruth = false;

        if (this.left instanceof Integer){
            Integer index = (Integer) this.left;
            leftTruth = model.get(index);
        }
        else {
            LogicalOperator logicPremise = (LogicalOperator) this.left;
            leftTruth = logicPremise.evaluate(model);
        }

        if (this.right instanceof Integer){
            Integer index = (Integer) this.right;
            rightTruth = model.get(index);
        }
        else {
            LogicalOperator logicPremise = (LogicalOperator) this.right;
            rightTruth = logicPremise.evaluate(model);
        }

        return (leftTruth && rightTruth) || (!leftTruth && !rightTruth) ;
    }

    @Override
    public String toString() {
        return "Biimplication{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
