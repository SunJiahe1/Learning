package com.example.tradingPlatform.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        try {
//            String path = ResourceUtils.getURL("classpath:").getPath()+"static/upload/";
//            registry.addResourceHandler("/image/**").addResourceLocations("file:" + path);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
