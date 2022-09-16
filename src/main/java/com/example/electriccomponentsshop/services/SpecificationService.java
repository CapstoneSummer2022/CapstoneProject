package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.SpecificationDto;
import com.example.electriccomponentsshop.entities.Specification;

import java.util.List;

public interface SpecificationService {

    SpecificationDto convertToDto(Specification specification);

    List<SpecificationDto> findSpecificationsBySpecificationIdNotIn(List<Integer> list);

    Specification getById(String id);

    List<SpecificationDto> findAll();
}
