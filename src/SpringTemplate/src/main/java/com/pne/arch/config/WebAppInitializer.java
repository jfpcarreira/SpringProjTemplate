package com.pne.arch.config;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.*;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		createRootContext(servletContext);
		createMvcContext(servletContext);
		configureContextParameters(servletContext);
		registerFilters(servletContext);
	}

	private void createRootContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class); //Also valid: rootContext.setConfigLocation("com.pne.arch.config");
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

	private void createMvcContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(WebMvcConfig.class);

		ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", new DispatcherServlet(mvcContext));
		appServlet.setLoadOnStartup(1);
		Set<String> mappingConflicts = appServlet.addMapping("/");

		if (!mappingConflicts.isEmpty()) {
			throw new IllegalStateException("'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
		}
	}

	private void configureContextParameters(ServletContext servletContext) {
		servletContext.setInitParameter("defaultHtmlEscape", "true");
	}

	private void registerFilters(ServletContext servletContext) {
		registerSpringSecurityFilter(servletContext);
		registerEncodingFilter(servletContext);
	}

	private void registerSpringSecurityFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic securityFilter = servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"));
		securityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

	private void registerEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
		characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
	}
}
