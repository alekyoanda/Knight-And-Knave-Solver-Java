package knightAndKnaveSolver.logicalOperator;

import java.util.ArrayList;

public class Not extends LogicalOperator{
    private Object operand;

    public Not(Object operand){
        this.operand = operand;
    }

    @Override
    public boolean evaluate(ArrayList<Boolean> model) {
        if (this.operand instanceof Integer){
            Integer index = (Integer) this.operand;
            return !(model.get(index));
        }
        else {
            LogicalOperator logicPremise = (LogicalOperator) this.operand;
            return !(logicPremise.evaluate(model));
        }
    }

    @Override
    public String toString() {
        return "Not{" +
                "operand=" + operand +
                '}';
    }
}
