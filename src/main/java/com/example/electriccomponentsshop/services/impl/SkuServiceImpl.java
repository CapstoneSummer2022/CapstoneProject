package com.example.electriccomponentsshop.services.impl;



import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.SkuDTO;
import com.example.electriccomponentsshop.entities.Sku;
import com.example.electriccomponentsshop.repositories.SkuRepository;
import com.example.electriccomponentsshop.services.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Optional<Sku> skuOptional = skuRepository.findById(id);
            if(skuOptional.isPresent()){
                return skuOptional.get();
            }
            else throw new NoSuchElementException("Không có sku này");

    }
    @Override
    public List<SkuDTO> getSkuDtoByProductId(String id){
        try{
            int pId = Integer.parseInt(id);
            List<Sku> skus = skuRepository.findSkusByProductId(pId);
            if(skus.isEmpty()){
                throw new NoSuchElementException("Không có dữ liệu sku cho sản phẩm này");
            }else return skus.stream().map(this::convertToDto).collect(Collectors.toList());
        }catch (NumberFormatException e){
            throw new NoSuchElementException("Không có dữ liệu sku cho sản phẩm này");
        }


    }
    @Override
    public <S extends Sku> S save(S entity) {
        return skuRepository.save(entity);
    }


}
