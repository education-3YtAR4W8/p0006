package education.p0006.rpn;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RpnSession implements Serializable {
    private static final long serialVersionUID = 1L;

    String rpnFormula;
    String infixNotationFormula;
    String calculationResult;
    Boolean isInvalidRpnFormula;

    public RpnSession() {
        rpnFormula = "";
        infixNotationFormula = "";
        calculationResult = "";
        isInvalidRpnFormula = false;
    }

    public void clearMessageFlags() {
        isInvalidRpnFormula = false;
    }
}
