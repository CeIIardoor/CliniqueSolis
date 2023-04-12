package info.cellardoor.CliniqueSolis.Statistiques.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {

    @GetMapping("/stats/revenus/{année}/")
    public String index() {
        return "test";
    }



}
