package shop.services.impl;

import shop.config.ModelMap;
import shop.db.dto.ProvinceDTO;
import shop.db.entities.Province;
import shop.db.repositories.ProvinceRepository;
import shop.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public ProvinceDTO convertToDto(Province province){
        return modelMap.modelMapper().map(province,ProvinceDTO.class);
    }
    @Override
    public List<ProvinceDTO> findAll() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ProvinceDTO findByName(String name) {
        Optional<Province> provinceOptional =provinceRepository.findByName(name);
        if(provinceOptional.isEmpty()){
            throw new NoSuchElementException("Không tìm thấy tỉnh này");
        }
        return modelMap.modelMapper().map(provinceOptional.get(),ProvinceDTO.class);
    }
}