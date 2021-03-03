package education.p0006.rpn.rpnController.validationTests;

import education.p0006.rpn.RpnController;
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
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NumberTests {
    private MockMvc mockMvc;

    @Autowired
    RpnController target;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void 入力値が半角の負の整数2147483648である場合エラーメッセージが表示されないこと() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "-2147483648"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("-2147483648"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("-2147483648"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値が半角の負の整数2147483648から正の整数147483647の有効範囲である場合エラーメッセージが表示されないこと() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "-37"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("-37"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("-37"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値が半角の正の整数2147483647である場合エラーメッセージが表示されないこと() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "2147483647"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("2147483647"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("2147483647"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値が半角の負の整数2147483649である場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "-2147483649"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("数字は-2147483648以上、2147483647以下の半角整数で入力してください。"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値が半角の正の整数2147483648である場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "2147483648"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("数字は-2147483648以上、2147483647以下の半角整数で入力してください。"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値が小数点を含む場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "1.5"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("数字は-2147483648以上、2147483647以下の半角整数で入力してください。"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 入力値が全角の数字である場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "１"))
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
