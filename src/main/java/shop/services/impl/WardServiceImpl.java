package shop.services.impl;

import shop.config.ModelMap;
import shop.db.dto.WardDTO;
import shop.db.entities.Ward;
import shop.db.repositories.WardRepository;
import shop.services.WardService;
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
        Optional<Ward> wardOptional= wardRepository.findByName(name);
        if(wardOptional.isEmpty()){
            throw  new NoSuchElementException("Không có xã/thị trấn này");
        }
        return convertToDto(wardOptional.get());
    }
}
