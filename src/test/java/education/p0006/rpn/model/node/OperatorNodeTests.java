package education.p0006.rpn.model.node;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class OperatorNodeTests {

    @Test
    public void OperatorNodeのインスタンス生成時に初期値が設定されること() throws Exception {
        // 前処理
        NumberNode leftNode = new NumberNode(2);
        NumberNode rightNode = new NumberNode(1);

        try {
            // 実行
            OperatorNode result = new OperatorNode(OperatorNode.Operator.Plus,rightNode,leftNode);

            // 検証
            assertThat(result.getOperator(), is(OperatorNode.Operator.Plus));
            assertThat(result.getLeft(), is(leftNode));
            assertThat(result.getRight(), is(rightNode));

        }catch (Exception ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void OperatorNodeのインスタンスを生成して計算結果が取得できること() throws Exception {
        // 前処理なし

        try {
            // 実行
            int result = new OperatorNode(OperatorNode.Operator.Plus,new NumberNode(1),new NumberNode(2)).calculate();

            // 検証
            assertThat(result, is(3));

        }catch (Exception ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void OperatorNodeのインスタンスを生成して中置記法が取得できること() throws Exception {
        // 前処理なし

        try {
            // 実行
            String result = new OperatorNode(OperatorNode.Operator.Plus,new NumberNode(1),new NumberNode(2)).convertInfixNotation();

            // 検証
            assertThat(result, is("2 + 1"));

        }catch (Exception ex){
            // 検証
            fail();
        }

        // 後処理なし
    }
}
