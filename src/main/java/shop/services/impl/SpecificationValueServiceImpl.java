package shop.services.impl;

import shop.config.ModelMap;
import shop.db.dto.SpecificationValueDto;
import shop.db.entities.SpecificationValue;
import shop.db.repositories.SpecificationValueRepository;
import shop.services.SpecificationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecificationValueServiceImpl implements SpecificationValueService {
    @Autowired
    ModelMap modelMap;
    @Autowired
    SpecificationValueRepository specificationValueRepository;
    @Override
    public SpecificationValueDto convertToDto(SpecificationValue specificationValue){
        return modelMap.modelMapper().map(specificationValue, SpecificationValueDto.class);
    }


}
