package education.p0006.rpn.model.node;


public interface Node {

    public abstract int calculate() throws Exception;

    public abstract String convertInfixNotation();
}
