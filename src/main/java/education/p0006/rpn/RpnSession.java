package education.p0006.rpn;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class RpnSession {
    private static final long serialVersionUID = 1L;
    public String formula;
    public Integer result;
}
