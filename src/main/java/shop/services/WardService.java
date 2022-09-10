package shop.services;

import shop.db.dto.WardDTO;
import shop.db.entities.Ward;

import java.util.List;

public interface WardService {
    List<WardDTO> findByDistrictName(String districtName);

    List<WardDTO> findAll();

    WardDTO findByName(String name);
    WardDTO convertToDto(Ward ward);
}
