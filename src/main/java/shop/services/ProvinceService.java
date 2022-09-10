package shop.services;

import shop.db.dto.ProvinceDTO;
import shop.db.entities.Province;

import java.util.List;

public interface ProvinceService {

    ProvinceDTO convertToDto(Province province);

    List<ProvinceDTO> findAll();

    ProvinceDTO findByName(String name);

}
