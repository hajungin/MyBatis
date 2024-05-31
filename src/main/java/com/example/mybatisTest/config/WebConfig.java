package com.example.mybatisTest.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@Data
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**";  //View 에서 사용할 경로
    private String savePath = "file:///c:/upload_files/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
