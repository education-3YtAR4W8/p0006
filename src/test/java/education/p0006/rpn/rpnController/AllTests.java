package education.p0006.rpn.rpnController;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        education.p0006.rpn.rpnController.happyTets.AllTests.class, // 疎通テスト
        education.p0006.rpn.rpnController.validationTests.AllTests.class, // 入力値テスト
        education.p0006.rpn.rpnController.validationTests.AllTests.class // バリエーションテスト
})

public class AllTests {
}
