package com.example.electriccomponentsshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ElectricComponentsShopApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ElectricComponentsShopApplication.class, args);
    }

}
