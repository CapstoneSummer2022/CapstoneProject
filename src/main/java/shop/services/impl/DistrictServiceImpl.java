package shop.services.impl;

import shop.config.ModelMap;
import shop.db.dto.DistrictDTO;
import shop.db.entities.District;
import shop.db.repositories.DistrictRepository;
import shop.services.DistrictService;


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
        Optional<District> districtOptional = districtRepository.findByName(name);
        if(districtOptional.isEmpty()){
            throw  new NoSuchElementException("Không có huyện này");
        }
        return convertToDto(districtOptional.get());
    }
}
