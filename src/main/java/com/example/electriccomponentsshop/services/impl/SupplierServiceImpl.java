package com.example.electriccomponentsshop.services.impl;



import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.SupplierDTO;
import com.example.electriccomponentsshop.entities.Supplier;
import com.example.electriccomponentsshop.repositories.SupplierRepository;
import com.example.electriccomponentsshop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public <S extends Supplier> S save(S entity) {
        return supplierRepository.save(entity);
    }
    @Override
    public SupplierDTO convertToDto(Supplier supplier){
        return modelMap.modelMapper().map(supplier,SupplierDTO.class);
    }
    @Override
    public Supplier getBySupplierId(String id){
        try{
            int sId = Integer.parseInt(id);
            Optional<Supplier> supplier = supplierRepository.findById(sId);
            if(supplier.isPresent()){
                return supplier.get();
            }else throw new NoSuchElementException("Không tìm thấy nhà cung cấp này");
        }catch (NumberFormatException e){
            throw  new NoSuchElementException("Không tìm nhà cung cấp này");
        }
    }
   @Override
   public SupplierDTO getDtoById(String id){
        return convertToDto(getBySupplierId(id));
   }
    @Override
    public List<SupplierDTO> getAllSupplier(){
        List<Supplier> supplierList = supplierRepository.findAll();
        if(supplierList.isEmpty()){
            throw new NoSuchElementException("Không tìm thấy nhà cung cấp");
        }
        return supplierList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @Override
    public Optional<Supplier> findById(Integer integer) {
        return supplierRepository.findById(integer);
    }
    @Override
    public void addSupplier(SupplierDTO supplierDTO){
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setActive(1);
        supplierRepository.save(supplier);
    }
    @Override
    public void updateSupplier(SupplierDTO supplierDTO,String id){
        Supplier supplier = getBySupplierId(id);
        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());
        supplierRepository.save(supplier);
    }
    @Override
    public void disableSupplier(String id){
        Supplier supplier = getBySupplierId(id);
        supplier.setActive(0);
        supplierRepository.save(supplier);
    }
    @Override
    public void enableSupplier(String id){
        Supplier supplier = getBySupplierId(id);
        supplier.setActive(1);
        supplierRepository.save(supplier);
    }
}
