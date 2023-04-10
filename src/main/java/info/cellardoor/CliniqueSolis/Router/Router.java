package info.cellardoor.CliniqueSolis.Router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/dashboard")
    public String dashboardIndex() {
        return "dashboard.html";
    }
}