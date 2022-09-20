package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.WardDTO;
import com.example.electriccomponentsshop.entities.Ward;
import com.example.electriccomponentsshop.repositories.WardRepository;
import com.example.electriccomponentsshop.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    WardRepository wardRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public List<WardDTO> findByDistrictName(String districtName) {
        List<Ward> wardList =  wardRepository.findByDistrictName(districtName);
        return wardList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<WardDTO> findByDistrictId(String dCode) {
        List<Ward> wardList =  wardRepository.findByDistrictDCode(dCode);
        return wardList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public WardDTO convertToDto(Ward ward){
        return modelMap.modelMapper().map(ward,WardDTO.class);
    }
    @Override
    public List<WardDTO> findAll() {
        List<Ward> wardList =  wardRepository.findAll();
        return wardList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public WardDTO findByName(String name) {

        return convertToDto(getByName(name));
    }
    @Override
    public Ward getByName(String name){
        Optional<Ward> wardOptional= wardRepository.findByName(name);
        if(wardOptional.isEmpty()){
            throw  new NoSuchElementException("Không có xã/thị trấn này");
        }
        return wardOptional.get();
    }
}
