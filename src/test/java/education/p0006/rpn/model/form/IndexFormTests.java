package education.p0006.rpn.model.form;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

public class IndexFormTests {


    @Test
    public void IndexFormのインスタンス生成時に初期値が設定されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            IndexForm result = new IndexForm();

            // 検証
            assertThat(result.getErrorMessage(), is(nullValue()));
            assertThat(result.getInfixNotation(), is("ex. (100 + 50 - 75) / 5"));
            assertThat(result.getCalculationResult(), is("ex. 15"));

        }catch (Exception ex){
            // 検証
            fail();
        }

        // 後処理なし
    }
}
