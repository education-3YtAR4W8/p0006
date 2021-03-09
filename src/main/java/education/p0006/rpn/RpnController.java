package education.p0006.rpn;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

@Controller
public class RpnController {
    @GetMapping(path = "/rpn")
    public String index(@RequestParam(name = "rpnFormula", defaultValue = "") String rpnFormula, Model model) {
        Page page = new Page();
        if(!rpnFormula.isEmpty()) {
            page.rpnFormula = rpnFormula;
            page.calculationResult = convertToInFormula(page.rpnFormula);
        }
        model.addAttribute("page", page);

        return "rpn/index";
    }

    public String convertToInFormula(String rpnFormula) {
        String[] formulaArray = rpnFormula.split("\\s");
        System.out.println(rpnFormula);

        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < formulaArray.length; i++){
            int left;
            int right;
            if(formulaArray[i].equals("+") || formulaArray[i].equals("-") || formulaArray[i].equals("*") || formulaArray[i].equals("/")){
                left = stack.pop();
                right = stack.pop();

                switch(formulaArray[i]){
                    case "+" : stack.push(right + left); ;break;
                    case "-" : stack.push(right - left); ;break;
                    case "*" : stack.push(right * left); ;break;
                    case "/" : stack.push(right / left); ;break;
                }
                System.out.println(stack);
            }else if(formulaArray[i].matches("[+-]?\\d*(\\.\\d+)?")){
                try{
                    stack.push(Integer.parseInt(formulaArray[i]));
                }
                catch (NumberFormatException ex) {
                    System.out.println(formulaArray[i] + "は数値ではありません");
                }
            }
        }
        return String.valueOf(stack.pop());
    }

    @Getter
    @Setter
    static public class Page{
        private String rpnFormula;
        private String inFormula;
        private String calculationResult;
    }

}
