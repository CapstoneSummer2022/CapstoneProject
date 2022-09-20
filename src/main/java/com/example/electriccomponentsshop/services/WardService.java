package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.WardDTO;
import com.example.electriccomponentsshop.entities.Ward;


import java.util.List;

public interface WardService {
    List<WardDTO> findByDistrictName(String districtName);

    List<WardDTO> findByDistrictId(String dCode);

    List<WardDTO> findAll();

    WardDTO findByName(String name);
    WardDTO convertToDto(Ward ward);

    Ward getByName(String name);
}
