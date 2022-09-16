package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.DistrictDTO;
import com.example.electriccomponentsshop.entities.District;


import java.util.List;

public interface DistrictService {
    List<DistrictDTO> findByProvinceName(String provinceName);

    DistrictDTO convertToDto(District district);

    List<DistrictDTO> findAll();

    DistrictDTO findByName(String name);
}
