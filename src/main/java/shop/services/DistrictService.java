package shop.services;

import shop.db.dto.DistrictDTO;
import shop.db.entities.District;

import java.util.List;

public interface DistrictService {
    List<DistrictDTO> findByProvinceName(String provinceName);

    DistrictDTO convertToDto(District district);

    List<DistrictDTO> findAll();

    DistrictDTO findByName(String name);
}
