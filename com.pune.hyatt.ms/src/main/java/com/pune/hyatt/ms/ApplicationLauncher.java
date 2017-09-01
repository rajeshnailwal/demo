package com.pune.hyatt.ms;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.pune.hyatt.ms.core.server.config.WebConfig;
import com.pune.hyatt.ms.remote.api.jobs.IJob;
import com.pune.hyatt.ms.remote.rmi.server.Server;
import com.pune.hyatt.ms.remote.rmi.server.callbacks.JobRecievedCallback;

//@Configuration
//@ComponentScan(basePackages = {"com.pune.hyatt.ms.core.server.config","com.pune.hyatt.ms.core.server.init", "com.pune.hyatt.ms.core.controller"})
/*@ComponentScan(basePackageClasses = {com.pune.hyatt.ms.core.server.config.WebConfig.class,
		com.pune.hyatt.ms.core.server.init.ApplicationInitializer.class, 
		com.pune.hyatt.ms.core.controller.DashboardController.class})
*/
@Import({WebConfig.class})
@ComponentScan(basePackages = {"com.pune.hyatt.ms.core.controller","com.pune.hyatt.ms.core.filter"})
@SpringBootApplication
public class ApplicationLauncher extends SpringBootServletInitializer {  
    
	private static boolean isRMIServerUp = false;
	
	public static void main(String[] args) {
		startRMIServer();
		while(!isRMIServerUp){}
    	SpringApplication.run(ApplicationLauncher.class, args);
    }
    
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ApplicationLauncher.class);
	}
	
	@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = 
                      new TomcatEmbeddedServletContainerFactory();
        factory.setContextPath("/ms/core/api");
        return factory;
     }
	
	private static void startRMIServer() {
		Thread serverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				isRMIServerUp = true;
				try {
					Server.start("localhost", new JobRecievedCallback() {
						
						@Override
						public void recieve(IJob job) {
							System.out.println(job.getClass().getName());
						}
					});
				} catch(RemoteException | MalformedURLException e){
					e.printStackTrace();;
				}
			}
		}); 
		
		serverThread.start();
	}
    
}
