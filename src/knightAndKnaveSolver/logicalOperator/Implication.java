package knightAndKnaveSolver.logicalOperator;

import java.util.ArrayList;

public class Implication implements LogicalOperator{
    private Object antecedent;
    private Object consequent;

    public Implication(Object antecedent, Object consequent){
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    @Override
    public boolean evaluate(ArrayList<Boolean> model) {
        boolean antecedentTruth;
        boolean consequentTruth;

        if (this.antecedent instanceof Integer){
            Integer index = (Integer) this.antecedent;
            antecedentTruth = model.get(index);
        }
        else {
            LogicalOperator logicPremise = (LogicalOperator) this.antecedent;
            antecedentTruth = logicPremise.evaluate(model);
        }

        if (this.consequent instanceof Integer){
            Integer index = (Integer) this.consequent;
            consequentTruth = model.get(index);
        }
        else {
            LogicalOperator logicPremise = (LogicalOperator) this.consequent;
            consequentTruth = logicPremise.evaluate(model);
        }

        return !antecedentTruth || consequentTruth;
    }

    @Override
    public String toString() {
        return "Implication{" +
                "antecedent=" + antecedent +
                ", consequent=" + consequent +
                '}';
    }
}
