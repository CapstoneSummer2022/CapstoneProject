package com.example.electriccomponentsshop.config;

import com.example.electriccomponentsshop.dto.AccountDTO;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMap {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;

    }
}
