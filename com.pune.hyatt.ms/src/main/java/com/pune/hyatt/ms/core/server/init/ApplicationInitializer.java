package com.pune.hyatt.ms.core.server.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext containerCtx) throws ServletException {
		System.out.println("*******************************************************Rajesh");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setServletContext(containerCtx);
		
		ServletRegistration.Dynamic dispatcherServlet = containerCtx.addServlet("dispatcher", new DispatcherServlet(ctx));
		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.addMapping("/");
	}

}
