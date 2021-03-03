package education.p0006.rpn.model.node;

import education.p0006.rpn.model.form.IndexForm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

public class NumberNodeTests {

    @Test
    public void NumberNodeのインスタンス生成時に初期値が設定されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            NumberNode result = new NumberNode(1);

            // 検証
            assertThat(result.getValue(), is(1));

        }catch (Exception ex){
            // 検証
            fail();
        }

        // 後処理なし
    }


    @Test
    public void NumberNodeのインスタンスを生成して計算結果が取得できること() throws Exception {
        // 前処理なし

        try {
            // 実行
            int result = new NumberNode(1).calculate();

            // 検証
            assertThat(result, is(1));

        }catch (Exception ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void NumberNodeのインスタンスを生成して中置記法が取得できること() throws Exception {
        // 前処理なし

        try {
            // 実行
            String result = new NumberNode(1).convertInfixNotation();

            // 検証
            assertThat(result, is("1"));

        }catch (Exception ex){
            // 検証
            fail();
        }

        // 後処理なし
    }
}
