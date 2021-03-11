package education.p0006.rpn.node;

import com.sun.org.apache.xpath.internal.operations.Div;
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

    private boolean needBraceForLeft() {
        if (left instanceof OperatorNode) {
            if (operatorType == OperatorType.Multiplication || operatorType == OperatorType.Division) {
                if (((OperatorNode) left).operatorType == OperatorType.Addition || ((OperatorNode) left).operatorType == OperatorType.Subtraction) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean needBraceForRight() {
        if (right instanceof OperatorNode) {
            if (operatorType == OperatorType.Multiplication || operatorType == OperatorType.Division) {
                if (((OperatorNode) right).operatorType == OperatorType.Addition || ((OperatorNode) right).operatorType == OperatorType.Subtraction) {
                    return true;
                }
            }

            if (operatorType == OperatorType.Subtraction && ((OperatorNode) right).operatorType == OperatorType.Subtraction) {
                return true;
            }

            if (operatorType == OperatorType.Division && ((OperatorNode) right).operatorType == OperatorType.Division) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getInfixNotationFormula() {
        String leftFormula = String.format(needBraceForLeft() ? "(%s)" : "%s", left.getInfixNotationFormula());
        String rightFormula = String.format(needBraceForRight() ? "(%s)" : "%s", right.getInfixNotationFormula());
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
