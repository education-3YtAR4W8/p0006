package education.p0006.rpn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperatorNode extends Node {
    OperatorEnum type;
    Node left;
    Node right;

    Integer calculate() {
        switch (type) {
            case Plus:
                return right.calculate() + left.calculate();
            case Minus:
                return right.calculate() - left.calculate();
            case Multiplication:
                return right.calculate() * left.calculate();
            case Divide:
                return right.calculate() / left.calculate();
        }
        return null;
    }
    public String toString() {
        switch (type) {
            case Plus:
                return right.toString() + OperatorEnum.Plus.getOperator() + left.toString();
            case Minus:
                return right.toString() + OperatorEnum.Minus.getOperator() + left.toString();
            case Multiplication:
                StringBuilder MultiplicationValue;
                if (right.toString().length() > 1 && (right.toString().contains("+") || right.toString().contains("-"))) {
                    MultiplicationValue = new StringBuilder("(" + right.toString() + ")" + OperatorEnum.Multiplication.getOperator());
                } else {
                    MultiplicationValue = new StringBuilder(right.toString() + OperatorEnum.Multiplication.getOperator());
                }
                if (left.toString().length() > 1 && (left.toString().contains("+") || left.toString().contains("-"))) {
                    MultiplicationValue.append("(").append(left.toString()).append(")");
                } else {
                    MultiplicationValue.append(left.toString());
                }
                return MultiplicationValue.toString();
            case Divide:
                StringBuilder divideValue;
                if (right.toString().length() > 1 && (right.toString().contains("+") || right.toString().contains("-"))) {
                    divideValue = new StringBuilder("(" + right.toString() + ")" + OperatorEnum.Divide.getOperator());
                } else {
                    divideValue = new StringBuilder(right.toString() + OperatorEnum.Divide.getOperator());
                }
                if (left.toString().length() > 1 && (left.toString().contains("+") || left.toString().contains("-"))) {
                    divideValue.append("(").append(left.toString()).append(")");
                } else {
                    divideValue.append(left.toString());
                }
                return divideValue.toString();
        }
        return null;
    }
}
