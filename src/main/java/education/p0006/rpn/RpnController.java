package education.p0006.rpn;

import education.p0006.rpn.model.form.IndexForm;
import education.p0006.rpn.model.node.Node;
import education.p0006.rpn.model.node.NumberNode;
import education.p0006.rpn.model.node.OperatorNode;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.*;
import java.util.*;

@Controller
public class RpnController {

    ResourceBundle rb = ResourceBundle.getBundle("application");

    @GetMapping(path = "/rpn")
    public String index(Model model) {
        model.addAttribute("form",new IndexForm());
        return "rpn/index";
    }

    @PostMapping("/rpn/calc")
    public String calc(@RequestParam("rpnFormula") String rpnFormula, Model model){
        IndexForm form = new IndexForm();
        Node root;
        try{
            String[] rpnFormulaArray = rpnFormula.split(" ");
            if(!rpnFormula.isEmpty()){
                root = createNodes(rpnFormulaArray);
                form.setCalculationResult(String.valueOf(root.calculate()));
                form.setInfixNotation(root.convertInfixNotation());
            }
        }catch (Exception ex){
            form.setErrorMessage(ex.getMessage());
        }
        model.addAttribute("form",form);
        return "rpn/index";
    }

    private Node createNodes(String[] rpnFormulaArray) throws Exception {
        Deque<Node> deque = new ArrayDeque<>();
        boolean isOperator = false;
        Node result;

        try{
            for (int i = 0; i < rpnFormulaArray.length; i++) {
                if(NumberUtils.isNumber(rpnFormulaArray[i])){
                    deque.push(new NumberNode(Integer.parseInt(rpnFormulaArray[i])));
                } else{
                    for (OperatorNode.Operator operator : OperatorNode.Operator.values()){
                        if(operator.getMark().equals(rpnFormulaArray[i])){
                            deque.push(new OperatorNode(operator,deque.pop(),deque.pop()));
                            isOperator = true;
                            break;
                        }
                    }
                    if(!isOperator){
                        throw new Exception();
                    }
                }
            }
            if(deque.size() != 1){
                // 根ノード以外が存在する場合は不正な計算とする
                throw new Exception();
            }

            result = deque.pop();

            // Numberノードのみがpopされた場合は中置記法の文字列制御のためフラグをセットする
            if(result.getClass() == NumberNode.class){
                ((NumberNode) result).setSingleNumberNode(true);
            }
        } catch(NumberFormatException ex){
            throw new Exception("数字は-2147483648以上、2147483647以下の半角整数で入力してください。");
        }catch (Exception ex){
            throw new Exception("計算式が不正です。");
        }

        return result;
    }

}
