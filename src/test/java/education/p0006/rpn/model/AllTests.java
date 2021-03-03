package education.p0006.rpn.model;

import education.p0006.rpn.model.form.IndexForm;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        education.p0006.rpn.model.form.AllTests.class, // formテスト
        education.p0006.rpn.model.node.AllTests.class // nodeテスト
})

public class AllTests {
}
