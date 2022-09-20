package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.SpecificationValueDto;
import com.example.electriccomponentsshop.entities.SpecificationValue;

public interface SpecificationValueService {
    SpecificationValueDto convertToDto(SpecificationValue specificationValue);

    <S extends SpecificationValue> S save(S entity);
}
