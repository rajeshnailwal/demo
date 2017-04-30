package practice.spring.code.rest;

import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

import practice.spring.code.rest.controller.PurchaseServiceController;

@SpringBootApplication
@ComponentScan(basePackages = {"practice.spring.code.rest.controller"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * This Bean is used to customize embedded tomcat
	 * by default tomcat uses port 8080 here we changing
	 * the default port to 8900. If we don't use this bean
	 * everything works fine with tomcat running on port 8080
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	    factory.setPort(8900);
	    factory.setSessionTimeout(10, TimeUnit.MINUTES);
	    //factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
	    return factory;
	}
}
