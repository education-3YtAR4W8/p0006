package education.p0006.rpn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberNode extends Node {
    Integer value;

    Integer calculate() {
        return value;
    }
    public String toString() {
        return value.toString();
    }
}
