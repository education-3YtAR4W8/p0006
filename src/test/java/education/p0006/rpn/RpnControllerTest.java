package education.p0006.rpn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RpnControllerTest {
    @Autowired
    RpnController sut;

    @Test
    public void getNode() {
        List<String> formulaList1 = new ArrayList<String>(Arrays.asList("3", "4", "+", "2", "-"));
        Optional<Node> result1 = Optional.ofNullable(sut.getNode(formulaList1));
        assertTrue(result1.isPresent());
        assertEquals((Integer) 5, result1.get().calculate());
        assertEquals("3+4-2", result1.get().toString());

        List<String> formulaList2 = new ArrayList<String>(Arrays.asList("3", "4", "*", "2", "/"));
        Optional<Node> result2 = Optional.ofNullable(sut.getNode(formulaList2));
        assertTrue(result2.isPresent());
        assertEquals((Integer) 6, result2.get().calculate());
        assertEquals("3*4/2", result2.get().toString());

        List<String> formulaList3 = new ArrayList<String>(Arrays.asList("3", "4", "+", "1", "2", "-", "*"));
        Optional<Node> result3 = Optional.ofNullable(sut.getNode(formulaList3));
        assertTrue(result3.isPresent());
        assertEquals("-7", result3.get().calculate().toString());
        assertEquals("(3+4)*(1-2)", result3.get().toString());
    }

    @Test
    public void checkFormula() {
        List<String> formulaList1 = new ArrayList<String>(Arrays.asList("3", "4", "+", "2", "-"));
        assertTrue(sut.checkFormula(formulaList1));

        List<String> formulaList2 = new ArrayList<String>(Arrays.asList("3", "4", "+", "1", "2", "-", "*"));
        assertTrue(sut.checkFormula(formulaList2));

        List<String> formulaList3 = new ArrayList<String>(Arrays.asList("3", "4", "&"));
        assertFalse(sut.checkFormula(formulaList3));

        List<String> formulaList4 = new ArrayList<String>(Arrays.asList("3", "4", "5"));
        assertFalse(sut.checkFormula(formulaList4));
    }
}