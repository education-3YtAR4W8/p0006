package education.p0006.rpn.rpnController.variationTests;

import education.p0006.rpn.rpnController.validationTests.NumberTests;
import education.p0006.rpn.rpnController.validationTests.OperatorTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InvaludVariationTests.class, // 不正バリエーションテスト
        ValidVariationTests.class // 有効バリエーションテスト
})

public class AllTests {
}
