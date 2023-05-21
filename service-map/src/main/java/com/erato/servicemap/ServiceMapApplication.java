package com.erato.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServiceMapApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class, args);
    }
    
    @Bean
    RestTemplate RestTemplate() {
        return new RestTemplate();
    }
    
}
