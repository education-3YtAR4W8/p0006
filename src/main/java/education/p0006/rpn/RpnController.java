package education.p0006.rpn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RpnController {
    @Autowired
    RpnSession rpnSession;

    @GetMapping(path = "/rpn")
    public String index(Model model) {
        model.addAttribute("page", rpnSession);
        return "rpn/index";
    }

    @PostMapping(path = "rpn/calculation")
    public String calculation(@RequestParam("rpnFormula")String inputFormula, Model model) {
        List<String> formulaList = Arrays.asList(inputFormula.split(" ", 0));

        if (!checkFormula(formulaList)) {
            rpnSession.formula = null;
            rpnSession.result = null;
            return "redirect:/rpn";
        }

        try {
            Node root = getNode(formulaList);
            rpnSession.formula = root.toString();
            rpnSession.result = root.calculate();
        } catch (Exception e) {
            rpnSession.formula = null;
            rpnSession.result = null;
        }
        return "redirect:/rpn";
    }

    public boolean checkFormula(List<String> formulaList) {
        int cntNumber = 0;
        int cntOperator = 0;
        List<String> operator = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
        for (String formula : formulaList) {
            if (formula.chars().allMatch( Character::isDigit)) {
                cntNumber++;
            } else if (operator.contains(formula)) {
                cntOperator++;
            } else {
                return false;
            }
        }
        if (cntNumber - cntOperator != 1) {
            return false;
        }
        return true;
    }

    public Node getNode(List<String> formulaList) {
        Stack<Node> que = new Stack<>();
        for (String data: formulaList) {
            if (OperatorEnum.Plus.getOperator().equals(data)) {
                que.add(new OperatorNode(OperatorEnum.Plus, que.pop(), que.pop()));
            } else if (OperatorEnum.Minus.getOperator().equals(data)) {
                que.add(new OperatorNode(OperatorEnum.Minus, que.pop(), que.pop()));
            } else if (OperatorEnum.Multiplication.getOperator().equals(data)) {
                que.add(new OperatorNode(OperatorEnum.Multiplication, que.pop(), que.pop()));
            } else if (OperatorEnum.Divide.getOperator().equals(data)) {
                que.add(new OperatorNode(OperatorEnum.Divide, que.pop(), que.pop()));
            } else {
                que.push(new NumberNode(Integer.parseInt(data)));
            }
        }
        return que.pop();
    }
}
