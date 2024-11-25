package com.paymybuddy.backend.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FrontWebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Rediriger toutes les routes non définies explicitement vers index.html
        registry.addViewController("/{segment1:[a-zA-Z0-9\\-_]+}")
        		.setViewName("forward:/index.html");
		registry.addViewController("/{segment1:[a-zA-Z0-9\\-_]+}/{segment2:[a-zA-Z0-9\\-_]+}")
		        .setViewName("forward:/index.html");
		registry.addViewController("/{segment1:[a-zA-Z0-9\\-_]+}/{segment2:[a-zA-Z0-9\\-_]+}/{segment3:[a-zA-Z0-9\\-_]+}")
		        .setViewName("forward:/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Servir les fichiers statiques
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}