package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.SpecificationValueDto;
import com.example.electriccomponentsshop.entities.SpecificationValue;
import com.example.electriccomponentsshop.repositories.SpecificationValueRepository;
import com.example.electriccomponentsshop.services.SpecificationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecificationValueServiceImpl implements SpecificationValueService {
    @Autowired
    ModelMap modelMap;
    @Autowired
    SpecificationValueRepository specificationValueRepository;
    @Override
    public SpecificationValueDto convertToDto(SpecificationValue specificationValue){
        return modelMap.modelMapper().map(specificationValue, SpecificationValueDto.class);
    }


}
