package com.erato.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 在 configuration load interceptors 时手动，
     * 提前强制 initialize BEAN jwtInterceptor,  也就会提前强制 initialize BEAN jwtInterceptor 所需要的 BEAN, e.g. StringRedisTemplate
     * 或说，提前强制让 jwtInterceptor 作为一个BEAN 身份来 initialize, 而非 as an interceptor
     * */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                //path need to be intercepted
                .addPathPatterns("/**")
                //path don't need to
                .excludePathPatterns("/noauthTest")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check");
    }
}
