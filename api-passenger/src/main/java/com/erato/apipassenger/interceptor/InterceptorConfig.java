package com.erato.apipassenger.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                //path need to be intercepted
                .addPathPatterns("/**")
                //path don't need to
                .excludePathPatterns("/noauthTest");
    }
}
