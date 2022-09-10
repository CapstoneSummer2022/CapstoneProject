package shop.services;

import shop.db.dto.SpecificationDto;
import shop.db.entities.Specification;

import java.util.List;

public interface SpecificationService {

    SpecificationDto convertToDto(Specification specification);

    List<SpecificationDto> findSpecificationsBySpecificationIdNotIn(List<Integer> list);
}
