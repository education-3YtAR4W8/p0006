package education.p0006.rpn;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OperatorType {
    Addition("+"), Subtraction("-"), Multiplication("*"), Division("/");

    private String symbol;

    private OperatorType(String symbol) {
        this.symbol = symbol;
    }

    public static OperatorType of(String symbol) {
        return Arrays.stream(OperatorType.values())
                .filter(it -> it.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("%sに紐づく演算子はありません。", symbol)));
    }

    public static boolean isOperator(String symbol) {
        return Arrays.stream(OperatorType.values())
                .anyMatch(it -> it.symbol.equals(symbol));
    }
}
