package com.erato.serviceprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableFeignClients
//@MapperScan("com.erato")
public class ServicePriceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServicePriceApp.class, args);
    }
}
