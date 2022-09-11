package shop.services;


import shop.db.dto.SkuDTO;
import shop.db.entities.Sku;

public interface SkuService {
    SkuDTO convertToDto(Sku sku);

    Sku getSkuById(String id);

    <S extends Sku> S save(S entity);
}
