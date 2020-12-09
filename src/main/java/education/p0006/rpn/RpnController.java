package education.p0006.rpn;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RpnController {
    @GetMapping(path = "/rpn")
    public String index() {
        return "rpn/index";
    }
}
