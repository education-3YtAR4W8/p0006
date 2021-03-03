package education.p0006.rpn.rpnController.validationTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NumberTests.class, // 数値テスト
        OperatorTests.class // 演算子テスト
})

public class AllTests {
}
