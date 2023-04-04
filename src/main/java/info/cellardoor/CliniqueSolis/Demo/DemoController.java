package info.cellardoor.CliniqueSolis.Demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo-controller")
public class DemoController {
        @RequestMapping("/hello")
        public ResponseEntity<String> helloWorld() {
                return ResponseEntity.ok("Hello World!");
        }
}