package education.p0006.rpn;

import education.p0006.rpn.node.CalculationNode;
import education.p0006.rpn.node.OperatorNode;
import education.p0006.rpn.node.ValueNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RpnControllerTest {
    @Autowired
    RpnController sut;

    @Test
    public void createTreeFromRpnFormulaは引数の逆ポーランド記法をノードに分解しツリー状にして返す() {
        CalculationNode root;

        root = sut.createTreeFromRpnFormula("1 2 +");
        assertEquals("o: + [v: 1] (v: 2)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("4 3 -");
        assertEquals("o: - [v: 4] (v: 3)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("2 4 *");
        assertEquals("o: * [v: 2] (v: 4)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("4 2 /");
        assertEquals("o: / [v: 4] (v: 2)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("1 2 3 * +");
        assertEquals("o: + [v: 1] (o: * [v: 2] (v: 3))", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("1 2 + 3 *");
        assertEquals("o: * [o: + [v: 1] (v: 2)] (v: 3)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("2 3 * 1 +");
        assertEquals("o: + [o: * [v: 2] (v: 3)] (v: 1)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("2 3 1 + *");
        assertEquals("o: * [v: 2] (o: + [v: 3] (v: 1))", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("6 4 2 / -");
        assertEquals("o: - [v: 6] (o: / [v: 4] (v: 2))", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("6 4 - 2 /");
        assertEquals("o: / [o: - [v: 6] (v: 4)] (v: 2)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("4 2 / 6 -");
        assertEquals("o: - [o: / [v: 4] (v: 2)] (v: 6)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("4 6 2 - /");
        assertEquals("o: / [v: 4] (o: - [v: 6] (v: 2))", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("8 4 / 2 /");
        assertEquals("o: / [o: / [v: 8] (v: 4)] (v: 2)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("8 4 2 / /");
        assertEquals("o: / [v: 8] (o: / [v: 4] (v: 2))", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("3 2 - 1 -");
        assertEquals("o: - [o: - [v: 3] (v: 2)] (v: 1)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("3 2 1 - -");
        assertEquals("o: - [v: 3] (o: - [v: 2] (v: 1))", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("4 2 / 6 3 / *");
        assertEquals("o: * [o: / [v: 4] (v: 2)] (o: / [v: 6] (v: 3))", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("1 2 + 3 * 3 /");
        assertEquals("o: / [o: * [o: + [v: 1] (v: 2)] (v: 3)] (v: 3)", serializeCalculationNode(root));

        root = sut.createTreeFromRpnFormula("4 6 + 5 2 - * 1 2 + 3 2 1 * + * /");
        assertEquals("o: / [o: * [o: + [v: 4] (v: 6)] (o: - [v: 5] (v: 2))] (o: * [o: + [v: 1] (v: 2)] (o: + [v: 3] (o: * [v: 2] (v: 1))))", serializeCalculationNode(root));
    }

    @Test
    public void nodeのcalculateは正しい計算結果を返す() {
        CalculationNode root;

        root = sut.createTreeFromRpnFormula("1 2 +");
        assertEquals(3, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("4 3 -");
        assertEquals(1, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("2 4 *");
        assertEquals(8, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("4 2 /");
        assertEquals(2, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("1 2 3 * +");
        assertEquals(7, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("1 2 + 3 *");
        assertEquals(9, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("2 3 * 1 +");
        assertEquals(7, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("2 3 1 + *");
        assertEquals(8, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("6 4 2 / -");
        assertEquals(4, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("6 4 - 2 /");
        assertEquals(1, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("4 2 / 6 -");
        assertEquals(-4, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("4 6 2 - /");
        assertEquals(1, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("4 2 / 6 3 / *");
        assertEquals(4, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("1 2 + 3 * 3 /");
        assertEquals(3, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("8 4 / 2 /");
        assertEquals(1, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("8 4 2 / /");
        assertEquals(4, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("3 2 - 1 -");
        assertEquals(0, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("3 2 1 - -");
        assertEquals(2, root.calculate().intValue());

        root = sut.createTreeFromRpnFormula("4 6 + 5 2 - * 1 2 + 3 2 1 * + * /");
        assertEquals(2, root.calculate().intValue());
    }

    @Test
    public void nodeのgetInfixNotationFormulaは中置記法の式を返す() {
        CalculationNode root;

        root = sut.createTreeFromRpnFormula("1 2 +");
        assertEquals("1 + 2", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("4 3 -");
        assertEquals("4 - 3", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("2 4 *");
        assertEquals("2 * 4", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("4 2 /");
        assertEquals("4 / 2", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("1 2 3 * +");
        assertEquals("1 + 2 * 3", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("1 2 + 3 *");
        assertEquals("(1 + 2) * 3", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("2 3 * 1 +");
        assertEquals("2 * 3 + 1", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("2 3 1 + *");
        assertEquals("2 * (3 + 1)", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("6 4 2 / -");
        assertEquals("6 - 4 / 2", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("6 4 - 2 /");
        assertEquals("(6 - 4) / 2", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("4 2 / 6 -");
        assertEquals("4 / 2 - 6", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("4 6 2 - /");
        assertEquals("4 / (6 - 2)", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("4 2 / 6 3 / *");
        assertEquals("4 / 2 * 6 / 3", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("1 2 + 3 * 3 /");
        assertEquals("(1 + 2) * 3 / 3", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("8 4 / 2 /");
        assertEquals("8 / 4 / 2", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("8 4 2 / /");
        assertEquals("8 / (4 / 2)", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("3 2 - 1 -");
        assertEquals("3 - 2 - 1", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("3 2 1 - -");
        assertEquals("3 - (2 - 1)", root.getInfixNotationFormula());

        root = sut.createTreeFromRpnFormula("4 6 + 5 2 - * 1 2 + 3 2 1 * + * /");
        assertEquals("(4 + 6) * (5 - 2) / (1 + 2) * (3 + 2 * 1)", root.getInfixNotationFormula());
    }

    private String serializeCalculationNode(CalculationNode node) {
        if (node instanceof ValueNode) {
            ValueNode valueNode = (ValueNode) node;
            return String.format("v: %d", valueNode.getValue());
        } else {
            OperatorNode operatorNode = (OperatorNode) node;
            return String.format("o: %s [%s] (%s)",
                    operatorNode.getOperatorType().getSymbol(),
                    serializeCalculationNode(operatorNode.getLeft()),
                    serializeCalculationNode(operatorNode.getRight()));
        }
    }


}
