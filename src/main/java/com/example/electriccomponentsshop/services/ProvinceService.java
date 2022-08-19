package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.ProvinceDTO;
import com.example.electriccomponentsshop.entities.Province;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {

    ProvinceDTO convertToDto(Province province);

    List<ProvinceDTO> findAll();

    ProvinceDTO findByName(String name);

}
