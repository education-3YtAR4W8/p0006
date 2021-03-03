package education.p0006.rpn;

public enum OperatorEnum {
    Plus("+"),
    Minus("-"),
    Multiplication("*"),
    Divide("/");

    private final String operator;

    OperatorEnum(String operator) {
        this.operator = operator;
    }
    String getOperator() {
        return operator;
    }
}
