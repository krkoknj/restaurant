package com.toyproject.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(RestaurantApplication.class)
                .profiles("spring.config.name:application", "spring.config.location.classpath:config")
                .build().run(RestaurantApplication.class, args);
    }

}
