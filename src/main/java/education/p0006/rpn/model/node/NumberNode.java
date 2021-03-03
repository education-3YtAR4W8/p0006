package education.p0006.rpn.model.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class NumberNode implements Node {

    private int value;

    private boolean isSingleNumberNode = false;

    public NumberNode(int value){
        this.value = value;
    }

    @Override
    public int calculate() {
        return this.value;
    }

    @Override
    public String convertInfixNotation() {
        if(this.value >= 0 || isSingleNumberNode){
            return String.valueOf(this.value);
        } else {
            return "(" + String.valueOf(this.value) + ")";
        }
    }

}
