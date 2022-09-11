package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.SkuDTO;
import com.example.electriccomponentsshop.entities.Sku;

public interface SkuService {
    SkuDTO convertToDto(Sku sku);

    Sku getSkuById(String id);

    <S extends Sku> S save(S entity);
}
