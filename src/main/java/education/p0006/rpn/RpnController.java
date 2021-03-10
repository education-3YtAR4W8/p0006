package education.p0006.rpn;

import education.p0006.rpn.node.CalculationNode;
import education.p0006.rpn.node.OperatorNode;
import education.p0006.rpn.node.ValueNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.ScatteringByteChannel;
import java.util.*;

@Controller
@SessionAttributes(types = {RpnSession.class})
public class RpnController {
    @AllArgsConstructor
    class RpnPage {
        RpnSession rpnSession;
    }

    @GetMapping(path = "/rpn")
    public String index(Model model, RpnSession rpnSession) {
        model.addAttribute("page", new RpnPage(rpnSession));
        return "rpn/index";
    }

    public CalculationNode createTreeFromRpnFormula(String rpnFormula) {
        Stack<CalculationNode> workingStack = new Stack<>();
        Arrays.stream(rpnFormula.split(" ")).forEach(it -> {
            if (OperatorType.isOperator(it)) {
                OperatorType operatorType = OperatorType.of(it);
                CalculationNode right = workingStack.pop();
                CalculationNode left = workingStack.pop();

                OperatorNode operatorNode = new OperatorNode(operatorType, left, right);
                workingStack.push(operatorNode);
            } else {
                Integer value = Integer.parseInt(it);
                ValueNode valueNode = new ValueNode(value);
                workingStack.push(valueNode);
            }
        });

        CalculationNode root = workingStack.pop();
        if (!workingStack.empty()) {
            throw new RuntimeException();
        }

        return root;
    }

    @PostMapping(path="/rpm")
    public String calculate(@RequestParam("rpnFormula")String rpnFormula, RpnSession rpnSession) {
        rpnSession.clearMessageFlags();
        rpnSession.rpnFormula = rpnFormula;
        try {
            CalculationNode root = createTreeFromRpnFormula(rpnFormula);
            rpnSession.infixNotationFormula = root.getInfixNotationFormula();
            rpnSession.calculationResult = root.calculate().toString();
        } catch (Exception e) {
            rpnSession.isInvalidRpnFormula = true;
        }

        return "redirect:/rpn";
    }
}
