package com.dawn.dawn.common.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenliming
 * @date 2023/9/16 0:00
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String basePath;
    @Value("${file.path}")
    private String filePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**",filePath+"/**").addResourceLocations("file:"+basePath);
    }
}
