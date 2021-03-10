package education.p0006.rpn.node;

import lombok.AllArgsConstructor;
import org.assertj.core.util.VisibleForTesting;

@AllArgsConstructor
public class ValueNode implements CalculationNode {
    private Integer value;

    @Override
    public String getInfixNotationFormula() {
        return value.toString();
    }

    @Override
    public Integer calculate() {
        return value;
    }

    @VisibleForTesting
    public Integer getValue() {
        return value;
    }
}
