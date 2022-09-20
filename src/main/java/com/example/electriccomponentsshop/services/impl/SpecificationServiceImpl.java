package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.SpecificationDto;
import com.example.electriccomponentsshop.entities.Specification;
import com.example.electriccomponentsshop.repositories.SpecificationRepository;
import com.example.electriccomponentsshop.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    ModelMap modelMap;
    @Autowired
    SpecificationRepository specificationRepository;
    @Override
    public SpecificationDto convertToDto(Specification specification){
        return modelMap.modelMapper().map(specification,SpecificationDto.class);
    }

    @Override
    public boolean addNewSpecification(SpecificationDto specificationDto){

        Optional<Specification> specificationOptional = specificationRepository.findSpecificationsByName(specificationDto.getName());
        if(specificationOptional.isPresent()){
            return false;
        }else {
            Specification specification = new Specification();
            specification.setName(specificationDto.getName());
            specificationRepository.save(specification);
            return true;
        }
    }

    @Override
    public List<SpecificationDto> findSpecificationsBySpecificationIdNotIn(List<Integer> list) {

        List<Specification> specifications = specificationRepository.findSpecificationsByIdNotIn(list.toArray(new Integer[list.size()]));
        return specifications.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @Override
    public Specification getById(String id){
        try{
            int sId= Integer.parseInt(id);
            Optional<Specification> s = specificationRepository.findById(sId);
            if(s.isPresent()){
                return s.get();
            }else throw new NoSuchElementException("Không có thông số này");
        }catch (NumberFormatException e){
            throw new NoSuchElementException("Không có thông số này");
        }
    }
    @Override
    public List<SpecificationDto> findAll(){
        List<Specification> specifications = specificationRepository.findAll();
        if(specifications.isEmpty()){
            throw new NoSuchElementException("Không có dữ liệu thông số");
        }else return specifications.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
