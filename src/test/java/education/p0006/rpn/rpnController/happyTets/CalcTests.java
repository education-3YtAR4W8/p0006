package education.p0006.rpn.rpnController.happyTets;

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
public class CalcTests {
    private MockMvc mockMvc;

    @Autowired
    RpnController target;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void エラーがスローされずメソッドが終了すること() throws Exception {
        // 前処理なし

        try {
            // 実行
            mockMvc.perform(post("/rpn/calc").param("rpnFormula", "1 2 +"))
                    // 検証
                    .andExpect(status().isOk())
                    .andExpect(view().name("rpn/index"))
                    .andExpect(model().attribute("form", hasProperty("errorMessage", is(nullValue()))))
                    .andExpect(model().attribute("form", hasProperty("calculationResult", is("3"))))
                    .andExpect(model().attribute("form", hasProperty("infixNotation", is("1 + 2"))));
        }catch (IOException ex){
            // 検証
            fail();
        }

        // 後処理なし
    }
}
