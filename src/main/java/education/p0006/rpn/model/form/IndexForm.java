package education.p0006.rpn.model.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class IndexForm {

    private String calculationResult;
    private String infixNotation;
    private String errorMessage;

    public IndexForm(){
        this.calculationResult = "ex. 15";
        this.infixNotation = "ex. (100 + 50 - 75) / 5";
        this.errorMessage = null;
    }
}
