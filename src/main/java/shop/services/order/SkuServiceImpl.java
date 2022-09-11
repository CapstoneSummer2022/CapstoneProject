package shop.services.order;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.config.ModelMap;
import shop.db.dto.SkuDTO;
import shop.db.entities.Sku;
import shop.db.repositories.SkuRepository;
import shop.services.SkuService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    SkuRepository skuRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public SkuDTO convertToDto(Sku sku){
        return modelMap.modelMapper().map(sku,SkuDTO.class);
    }
    @Override
    public Sku getSkuById(String id){
        try{
            int skuId = Integer.getInteger(id);
            Optional<Sku> skuOptional = skuRepository.findById(skuId);
            if(skuOptional.isPresent()){
                return skuOptional.get();
            }
            else throw new NoSuchElementException("Không có sku này");
        }catch (NumberFormatException e){
            throw new NoSuchElementException("Không có sku này");
        }
    }
    @Override
    public <S extends Sku> S save(S entity) {
        return skuRepository.save(entity);
    }
}