package shop.services;

import shop.db.dto.SpecificationValueDto;
import shop.db.entities.SpecificationValue;

public interface SpecificationValueService {
    SpecificationValueDto convertToDto(SpecificationValue specificationValue);
}
