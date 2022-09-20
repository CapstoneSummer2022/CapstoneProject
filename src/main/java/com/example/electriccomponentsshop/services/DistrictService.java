package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.DistrictDTO;
import com.example.electriccomponentsshop.dto.ProvinceDTO;
import com.example.electriccomponentsshop.entities.District;
import com.example.electriccomponentsshop.entities.Province;

import java.util.List;
import java.util.Optional;

public interface DistrictService {
    List<DistrictDTO> findByProvinceName(String provinceName);

    DistrictDTO convertToDto(District district);

    List<DistrictDTO> findAll();

    DistrictDTO findByName(String name);

    District getByName(String name);

    List<DistrictDTO> findByProvinceId(String provinceId);
}
