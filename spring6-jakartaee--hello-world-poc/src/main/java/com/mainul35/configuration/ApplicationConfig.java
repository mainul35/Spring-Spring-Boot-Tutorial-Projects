package com.mainul35.configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // root config
        AnnotationConfigWebApplicationContext objRootConfig = new AnnotationConfigWebApplicationContext();
        objRootConfig.register(RootConfig.class);
        objRootConfig.refresh();
        servletContext.addListener(new ContextLoaderListener(objRootConfig));

        // servlet config
        AnnotationConfigWebApplicationContext objRegister = new AnnotationConfigWebApplicationContext();
        objRegister.register(WebMvcConfig.class);
        ServletRegistration.Dynamic objRegistration =
                servletContext.addServlet("servlet",new DispatcherServlet(objRegister));

        // loadOnStartUp
        objRegistration.setLoadOnStartup(1);

        // mapping
        objRegistration.addMapping("/");
    }
}
