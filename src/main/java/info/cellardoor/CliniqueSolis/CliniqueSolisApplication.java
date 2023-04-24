package info.cellardoor.CliniqueSolis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CliniqueSolisApplication {

	public static void main(String[] args) {
	    ApplicationContext AppCtx = SpringApplication.run(CliniqueSolisApplication.class, args);
//		list all beans
//		for (String name : AppCtx.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}
	}

}