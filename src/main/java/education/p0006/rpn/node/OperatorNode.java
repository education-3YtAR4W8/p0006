package education.p0006.rpn.node;

import education.p0006.rpn.OperatorType;
import education.p0006.rpn.RpnController;
import lombok.AllArgsConstructor;
import org.assertj.core.util.VisibleForTesting;

@AllArgsConstructor
public class OperatorNode implements CalculationNode {
    private OperatorType operatorType;
    private CalculationNode left;
    private CalculationNode right;

    @VisibleForTesting
    public OperatorType getOperatorType() {
        return operatorType;
    }

    @VisibleForTesting
    public CalculationNode getLeft() {
        return left;
    }

    @VisibleForTesting
    public CalculationNode getRight() {
        return right;
    }


    @Override
    public String getInfixNotationFormula() {
        boolean needBraceForLeft = false;
        boolean needBraceForRight = false;
        if (operatorType == OperatorType.Multiplication || operatorType == OperatorType.Division) {
            if (left instanceof OperatorNode) {
                OperatorType operatorType = ((OperatorNode) left).operatorType;
                needBraceForLeft = (operatorType == OperatorType.Addition) || (operatorType == OperatorType.Subtraction);
            }
            if (right instanceof OperatorNode) {
                OperatorType operatorType = ((OperatorNode) right).operatorType;
                needBraceForRight = (operatorType == OperatorType.Addition) || (operatorType == OperatorType.Subtraction);
            }
        }
        String leftFormula = String.format(needBraceForLeft ? "(%s)" : "%s", left.getInfixNotationFormula());
        String rightFormula = String.format(needBraceForRight ? "(%s)" : "%s", right.getInfixNotationFormula());
        return String.format("%s %s %s", leftFormula, operatorType.getSymbol(), rightFormula);
    }

    @Override
    public Integer calculate() {
        switch (operatorType) {
            case Addition:
                return left.calculate() + right.calculate();
            case Subtraction:
                return left.calculate() - right.calculate();
            case Multiplication:
                return left.calculate() * right.calculate();
            case Division:
            default:
                return left.calculate() / right.calculate();
        }
    }
}
