package com.example.electriccomponentsshop.config;

import com.example.electriccomponentsshop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMap {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
