package shop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import shop.config.ModelMap;
import shop.db.dto.SkuDTO;
import shop.db.entities.Container;
import shop.db.entities.Sku;
import shop.db.repositories.SkuRepository;
import shop.services.SkuService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SkuServiceImpl implements SkuService {
    @Autowired
   SkuRepository skuRepository;
    @Autowired
    ModelMap modelMap;
    @Override
   public  SkuDTO convertToDto(Sku sku){
        return modelMap.modelMapper().map(sku,SkuDTO.class);
    }

    @Override
    public Sku getSkuById(String id) {
        try{
            int sId = Integer.parseInt(id);
            Optional<Sku> skuOptional = skuRepository.findById(sId);
            if(skuOptional.isPresent()){
                return skuOptional.get();
            }
            else  throw new NoSuchElementException("Không tìm thấy sku này");
        }catch (NumberFormatException e){
            throw  new NoSuchElementException("Không tìm thấy sku này");
        }
    }

    @Override
    public <S extends Sku> S save(S entity) {
        return null;
    }

    public List<Sku> findAll() {
        return skuRepository.findAll();
    }

}
