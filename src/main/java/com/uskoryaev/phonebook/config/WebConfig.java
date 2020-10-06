package com.uskoryaev.phonebook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>web config that responsible for routing app</p>
 *
 * @author Uskoryev Alexey
 * @version 1.0
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * The method is used pea routing to root website
     *
     * @param registry {@link ViewControllerRegistry}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}