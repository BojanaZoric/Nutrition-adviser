package sbnz.projekat.nutritionadviser;

import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NutritionAdviserApplication {

	public static void main(String[] args) {
		//SpringApplication.run(NutritionAdviserApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(NutritionAdviserApplication.class, args); 

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        StringBuilder sb = new StringBuilder("Application beans:\n");
        for (String beanName : beanNames) {
            sb.append(beanName + "\n");
        }
	}
	
	/*
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.projekat", "nutrition-adviser-drools", "0.0.1-SNAPSHOT"));
		
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}*/
}
