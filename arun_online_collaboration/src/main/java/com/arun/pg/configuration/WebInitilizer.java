package com.arun.pg.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitilizer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		//registering all the classess
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringConfiguration.class);
		ctx.register(HibernateConfiguration.class);
		ctx.register(SecurityConfig.class);
		ctx.setServletContext(container);

		// we are adding the springsecurity filter
//		container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
//				.addMappingForUrlPatterns(null, false, "/*");

		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");

	}

}