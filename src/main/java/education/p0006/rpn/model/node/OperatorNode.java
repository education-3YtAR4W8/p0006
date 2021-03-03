package education.p0006.rpn.model.node;

import lombok.Getter;

public final class OperatorNode implements Node {

    @Getter
    Operator operator;
    @Getter
    Node left;
    @Getter
    Node right;

    public OperatorNode(Operator operator,Node right,Node left){
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public enum Operator{
        Plus("+"),
        Minus("-"),
        Multiply("*"),
        Divide("/");

        private String mark;

        private Operator(String mark) {
            this.mark = mark;
        }

        public String getMark() {
            return mark;
        }
    }

    @Override
    public int calculate() throws Exception {
        int result = 0;
        try {
            switch (this.operator) {
                case Plus:
                    result = Math.addExact(this.left.calculate(),this.right.calculate()) ;
                    break;
                case Minus:
                    result = Math.subtractExact(this.left.calculate(),this.right.calculate());
                    break;
                case Multiply:
                    result = Math.multiplyExact(this.left.calculate(),this.right.calculate());
                    break;
                case Divide:
                    result = Math.floorDiv(this.left.calculate(),this.right.calculate());
                    break;
            }
        } catch(ArithmeticException ex){
            if(ex.getMessage().equals("/ by zero")){
                throw new Exception("ゼロ除算はできません");
            }else if(ex.getMessage().equals("integer overflow")) {
                throw new Exception("計算結果がオーバーフローしました");
            }else {
                throw new Exception(ex.getMessage());
            }
        } catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    @Override
    public String convertInfixNotation(){
        StringBuilder stringBuilder = new StringBuilder();
        switch (this.operator){
            case Plus:
                stringBuilder
                        .append(left.convertInfixNotation())
                        .append(" + ")
                        .append(this.right.convertInfixNotation());
                break;
            case Minus:
                stringBuilder
                        .append(left.convertInfixNotation())
                        .append(" - ")
                        .append(this.right.convertInfixNotation());
                break;
            case Multiply:
                if(this.left.getClass() == OperatorNode.class
                        && ((((OperatorNode)this.left).getOperator().equals(Operator.Plus)) || ((OperatorNode)this.left).getOperator().equals(Operator.Minus))){
                    stringBuilder
                            .append("(")
                            .append(this.left.convertInfixNotation())
                            .append(") * ");
                }else {
                    stringBuilder
                            .append(this.left.convertInfixNotation())
                            .append(" * ");
                }
                if(this.right.getClass() == OperatorNode.class
                        && ((((OperatorNode)this.right).getOperator().equals(Operator.Plus)) || ((OperatorNode)this.right).getOperator().equals(Operator.Minus))){
                    stringBuilder
                            .append("(")
                            .append(this.right.convertInfixNotation())
                            .append(")");
                }else {
                    stringBuilder
                            .append(this.right.convertInfixNotation());
                }
                break;
            case Divide:
                if(this.left.getClass() == OperatorNode.class
                        && ((((OperatorNode)this.left).getOperator().equals(Operator.Plus)) || ((OperatorNode)this.left).getOperator().equals(Operator.Minus))){
                    stringBuilder
                            .append("(")
                            .append(this.left.convertInfixNotation())
                            .append(") / ");
                }else {
                    stringBuilder
                            .append(this.left.convertInfixNotation())
                            .append(" / ");
                }
                if(this.right.getClass() == OperatorNode.class
                        && ((((OperatorNode)this.right).getOperator().equals(Operator.Plus)) || ((OperatorNode)this.right).getOperator().equals(Operator.Minus))){
                    stringBuilder
                            .append("(")
                            .append(this.right.convertInfixNotation())
                            .append(")");
                }else {
                    stringBuilder
                            .append(this.right.convertInfixNotation());
                }
                break;
        }

        return stringBuilder.toString();
    }
}
