package education.p0006.rpn.rpnController.happyTets;

import education.p0006.rpn.rpnController.validationTests.NumberTests;
import education.p0006.rpn.rpnController.validationTests.OperatorTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IndexTests.class, // indexメソッドテスト
        CalcTests.class // calcメソッドテスト
})

public class AllTests {
}
