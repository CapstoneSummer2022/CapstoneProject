package shop.services.impl;

import shop.config.ModelMap;
import shop.db.dto.SpecificationDto;
import shop.db.entities.Specification;
import shop.db.repositories.SpecificationRepository;
import shop.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<SpecificationDto> findSpecificationsBySpecificationIdNotIn(List<Integer> list) {

        List<Specification> specifications = specificationRepository.findSpecificationsByIdNotIn(list.toArray(new Integer[list.size()]));
        return specifications.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
