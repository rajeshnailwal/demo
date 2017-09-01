package com.pune.hyatt.ms.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class RedirectionFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Redirection Filter is working "+req.getClass().getName()+", "+res.getClass().getName());
		
		/*if(!"/ms/core/api/testme".equals(((RequestFacade)req).getRequestURI())) {
			System.out.println("Doesn't match");
			((ResponseFacade)res).sendRedirect("/redirectedto");
		}
		else {
			*/
		//}
		System.out.println(((RequestFacade)req).getRequestURI());
		System.out.println(((RequestFacade)req).getRequestURL());
		System.out.println(req.getServletContext().getContextPath());
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
