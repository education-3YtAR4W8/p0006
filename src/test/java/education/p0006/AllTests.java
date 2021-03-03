package education.p0006;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        education.p0006.rpn.model.AllTests.class, // modelテスト
        education.p0006.rpn.rpnController.AllTests.class // Controllerテスト
})

public class AllTests {
}
