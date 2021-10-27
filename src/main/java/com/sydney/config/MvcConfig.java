package com.sydney.config;

import com.sydney.handler.CherryHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //config: not need to implement individual controller request for every thymeleaf template
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //viewController: request path, viewName: redirect view file
        registry.addViewController("login").setViewName("logsign");

        registry.addViewController("index").setViewName("index");

        registry.addViewController("test").setViewName("test");

        registry.addViewController("goods").setViewName("goods");

        registry.addViewController("contact").setViewName("contact");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new CherryHandler());
        ir.addPathPatterns("/index","/usercenter");
        ir.excludePathPatterns("/login");
    }
}
