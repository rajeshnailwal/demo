package practice.spring.code.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import practice.spring.code.mvc.controller.PageController;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PageController.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
