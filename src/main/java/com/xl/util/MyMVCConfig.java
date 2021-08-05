package com.xl.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地 路径   D:\images\suyan3.jpg      网络地址路径:http://localhost:8080/uploads/suyan3.jpg
        // 网络地址映射路径
        // 映射出多级路径
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:H:\\测试目录\\上传目录\\");
    }
}

