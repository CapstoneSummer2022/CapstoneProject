package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.DistrictDTO;
import com.example.electriccomponentsshop.entities.District;
import com.example.electriccomponentsshop.repositories.DistrictRepository;
import com.example.electriccomponentsshop.services.DistrictService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public List<DistrictDTO> findByProvinceName(String provinceName) {
        List<District> districtList =districtRepository.findByProvinceName(provinceName);
        return districtList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @Override
    public DistrictDTO convertToDto(District district){
        return modelMap.modelMapper().map(district,DistrictDTO.class);
    }
    @Override
    public List<DistrictDTO> findAll() {
        List<District> districtList =districtRepository.findAll();
        return districtList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public DistrictDTO findByName(String name) {

        return convertToDto(getByName(name));
    }
    @Override
    public District getByName(String name){
        System.out.println(name+"huyện");
        Optional<District> districtOptional = districtRepository.findByName(name);
        if(districtOptional.isEmpty()){
            throw  new NoSuchElementException("Không có huyện này");
        }
        return districtOptional.get();
    }

    @Override
    public List<DistrictDTO> findByProvinceId(String provinceId) {
        List<District> districtList =districtRepository.findByPCode(provinceId);
        return districtList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
