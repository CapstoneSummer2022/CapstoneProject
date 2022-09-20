package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.WarehouseDTO;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.WarehouseRepository;
import com.example.electriccomponentsshop.services.DistrictService;
import com.example.electriccomponentsshop.services.ProvinceService;
import com.example.electriccomponentsshop.services.WardService;
import com.example.electriccomponentsshop.services.WarehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ModelMap modelMap;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DistrictService districtService;
    @Autowired
    WardService wardService;

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public WarehouseDTO convertToDto(Warehouse warehouse){
        return modelMap.modelMapper().map(warehouse,WarehouseDTO.class);

    }
    @Override
    public  List<WarehouseDTO>  getAllWarehouse(){
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        if(warehouseList.isEmpty()){
            throw new NoSuchElementException("Không có kho nào");
        }
        else {
            return warehouseList.stream().map(this::convertToDto).collect(Collectors.toList());
        }
    }
    @Override
    public Warehouse getWarehouse(String id){
        try{
            int wid = Integer.parseInt(id);
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(wid);
            if(warehouseOptional.isPresent()){
                return warehouseOptional.get();
            }else throw  new NoSuchElementException("Không có kho này");
        }catch (NumberFormatException e){
            throw new NoSuchElementException("Không có kho này");
        }
    }

    @Override
    public Page<Warehouse> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Warehouse> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Warehouse> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public void add(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setStoreId(1);
        Province province = provinceService.getByName(warehouseDTO.getProvinceName());
        District  district = districtService.getByName(warehouseDTO.getDistrictName());
        Ward ward = wardService.getByName(warehouseDTO.getWardName());
        warehouse.setProvinceWarehouse(province);
        warehouse.setDistrictWarehouse(district);
        warehouse.setWardWarehouse(ward);
        warehouse.setDetailLocation(warehouseDTO.getDetailLocation());
        warehouse.setStatus(1);
        warehouseRepository.save(warehouse);
    }
    @Override
    public void update(WarehouseDTO warehouseDTO,String id){
        Warehouse warehouse = getWarehouse(id);
        Province province = provinceService.getByName(warehouseDTO.getProvinceName());
        District  district = districtService.getByName(warehouseDTO.getDistrictName());
        Ward ward = wardService.getByName(warehouseDTO.getWardName());
        warehouse.setProvinceWarehouse(province);
        warehouse.setDistrictWarehouse(district);
        warehouse.setWardWarehouse(ward);
        warehouse.setDetailLocation(warehouseDTO.getDetailLocation());
        warehouseRepository.save(warehouse);
    }
    @Override
    public WarehouseDTO getWarehouseDtoById(String id){
        return convertToDto(getWarehouse(id));
    }
    @Override
    public void disableWarehouse(String id){
        Warehouse warehouse = getWarehouse(id);
        warehouse.setStatus(0);
        warehouseRepository.save(warehouse);
    }
    @Override
    public void enableWarehouse(String id){
        Warehouse warehouse = getWarehouse(id);
        warehouse.setStatus(1);
        warehouseRepository.save(warehouse);
    }
}
