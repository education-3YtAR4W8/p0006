package education.p0006.rpn;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum OperatorType {
    Addition("+"), Subtraction("-"), Multiplication("*"), Division("/");

    private String symbol;

    OperatorType(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<OperatorType> of(String symbol) {
        return Arrays.stream(OperatorType.values())
                .filter(it -> it.symbol.equals(symbol))
                .findFirst();
    }
}
