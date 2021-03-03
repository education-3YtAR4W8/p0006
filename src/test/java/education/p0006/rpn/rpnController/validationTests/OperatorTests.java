package education.p0006.rpn.rpnController.validationTests;

import education.p0006.rpn.RpnController;
import education.p0006.rpn.model.node.Node;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OperatorTests {
    private MockMvc mockMvc;

    @Autowired
    RpnController target;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void 入力値がOperatorNodeに変換可能かつ演算子がPlusである場合中置記法と計算結果が表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "3 4 +"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("7"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("3 + 4"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値がOperatorNodeに変換可能かつ演算子がMinusである場合中置記法と計算結果が表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "5 4 -"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("1"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("5 - 4"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値がOperatorNodeに変換可能かつ演算子がMultiplyである場合中置記法と計算結果が表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "6 7 *"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("42"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("6 * 7"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値がOperatorNodeに変換可能かつ演算子がDivideである場合中置記法と計算結果が表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "30 6 /"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("5"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("30 / 6"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値の文字列がOperatorと一致しない場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "30 6 a"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("計算式が不正です。"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void Operatorの数が不正である場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "10 2 + 1 2 + /　+"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("計算式が不正です。"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void Operatorの入力位置が不正である場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "10 - 2"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("計算式が不正です。"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

}
