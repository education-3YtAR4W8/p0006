package education.p0006.rpn.rpnController.variationTests;

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
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvaludVariationTests {
    private MockMvc mockMvc;

    @Autowired
    RpnController target;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void ゼロ除算を実行しようとした場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "10 0 /"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("ゼロ除算はできません"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 計算結果が負の整数2147483649以下である場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "-2147483648 1 -"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("計算結果がオーバーフローしました"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }

    @Test
    public void 計算結果が正の整数2147483648以上である場合エラーメッセージが表示されること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "2147483647 1 +"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is("計算結果がオーバーフローしました"))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("ex. 15"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("ex. (100 + 50 - 75) / 5"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }
}
