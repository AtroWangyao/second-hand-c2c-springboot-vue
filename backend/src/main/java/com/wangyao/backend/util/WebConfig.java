package com.wangyao.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("跨域ok");
        registry.addMapping("/**")//允许被访问的东西
                .allowedOrigins("Http://localhost:8080", "null") //允许访问的外域URL
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")//允许的方法
                .allowCredentials(true)//是否允许浏览器读取response的内容
                .maxAge(3600);//响应时间


    }
    //图片路径配置
    @Value("${upload-folder.content}")
    private  String UPLOAD_FOLDER;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("file:" + UPLOAD_FOLDER);
        //System.out.println("file:" +str);
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + UPLOAD_FOLDER);
        //super.addResourceHandlers(registry);
    }
}
