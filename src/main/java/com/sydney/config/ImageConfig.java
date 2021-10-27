package com.sydney.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageConfig implements WebMvcConfigurer {

    // use this configurer to map the local images to the urls
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //absolute path for the image storage
        String IMG_PATH = "/Users/chinone/Desktop/idlecherry-master/src/main/resources/static/img/";

        //set the mapping principle
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + IMG_PATH);

        //after mapping, access ip:host/idlecherry/images/**.jpg to access the local images

    }
}
