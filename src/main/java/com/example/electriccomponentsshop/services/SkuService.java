package com.example.electriccomponentsshop.services;


import com.example.electriccomponentsshop.dto.SkuDTO;
import com.example.electriccomponentsshop.entities.Sku;


import java.util.List;

public interface SkuService {
    SkuDTO convertToDto(Sku sku);

    Sku getSkuById(String id);

    List<SkuDTO> getSkuDtoByProductId(String pid);

    <S extends Sku> S save(S entity);


}
