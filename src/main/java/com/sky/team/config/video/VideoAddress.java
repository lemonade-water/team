package com.sky.team.config.video;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class VideoAddress implements WebMvcConfigurer {


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/video/**")
                .addResourceLocations("file:G:/视频资源/")
                .setCachePeriod(31556926);
    }
    //前端跨域
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")//设置允许跨域的路径
//                .allowedOrigins("*")//设置允许跨域请求的域名
//                .allowCredentials(true)//是否允许证书 不再默认开启
//                .allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
//                .maxAge(3600);//跨域允许时间
//    }

}
