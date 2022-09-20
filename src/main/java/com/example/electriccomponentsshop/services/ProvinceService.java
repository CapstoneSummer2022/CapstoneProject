package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.ProvinceDTO;
import com.example.electriccomponentsshop.entities.Province;

import java.util.List;

public interface ProvinceService {

    ProvinceDTO convertToDto(Province province);

    List<ProvinceDTO> findAll();

    ProvinceDTO findByName(String name);

    Province getByName(String name);
}
