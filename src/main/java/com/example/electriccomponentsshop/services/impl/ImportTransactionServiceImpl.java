package com.example.electriccomponentsshop.services.impl;




import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.ImportItemDto;
import com.example.electriccomponentsshop.dto.ImportTransactionDto;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.ImportItemRepository;
import com.example.electriccomponentsshop.repositories.ImportTransactionRepository;
import com.example.electriccomponentsshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImportTransactionServiceImpl implements ImportTransactionService {
    @Autowired
    ImportTransactionRepository importTransactionRepository;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    ImportItemRepository importItemRepository;
    @Autowired
    SupplierService supplierService;
    @Autowired
    ContainerService containerService;
    @Autowired
    ModelMap modelMap;
    @Autowired
    ProductService productService;
    @Autowired
    SkuService skuService;

    @Override
    public ImportTransactionDto convertToDto(ImportTransaction importTransaction){
        return modelMap.modelMapper().map(importTransaction,ImportTransactionDto.class);
    }
    @Override
    public ImportTransactionDto getDtoById(String id){
        return convertToDto(getById(id));
    }
    @Override
    public ImportTransaction getById(String id){
        try{
            int importId = Integer.parseInt(id);
            Optional<ImportTransaction> importTransactionOptional = importTransactionRepository.findById(importId);
            if(importTransactionOptional.isEmpty()){
                throw  new NoSuchElementException("Không có giao dịch này");
            }
            else return importTransactionOptional.get();
        }catch (NumberFormatException e){
            throw  new NoSuchElementException("Không có giao dịch này");
        }

    }
    @Override
    public List<ImportTransactionDto> findAll() {
        List<ImportTransaction> importTransactions = importTransactionRepository.findAll();
        if(importTransactions.isEmpty()){
            throw  new NoSuchElementException("Chưa có giao dịch nhập");
        }
        return importTransactions.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public boolean updateImportTransaction(ImportTransactionDto importTransactionDto){
        return true;
    }
    @Override
    public boolean addImportTransaction(ImportTransactionDto importTransactionDto) throws NoSuchElementException{
        ImportTransaction importTransaction= new ImportTransaction();
        BigDecimal totalPayment= new BigDecimal("0");
        importTransaction.setImportDate(Date.valueOf(importTransactionDto.getImportDate()));
        Supplier supplier = supplierService.getBySupplierId(importTransactionDto.getSupplierId());
        importTransaction.setSupplier(supplier);
        Warehouse warehouse  = warehouseService.getWarehouse(importTransactionDto.getWarehouseId());
        importTransaction.setWarehouseImport(warehouse);
        importTransaction.setTotalPayment(totalPayment);
        importTransaction = importTransactionRepository.save(importTransaction);
        List<ImportItem> importItems = new ArrayList<>();
        List<ImportItemDto> importItemDtos = importTransactionDto.getImportItems();
        System.out.println(importItemDtos.size() + "đây là");

        for (ImportItemDto im: importItemDtos
             ) {
            System.out.println("híadd");
                Container container = containerService.getContainerById(im.getContainerId());
            System.out.println(container.getRowIn()+ "cơ mà");
                Product product = productService.getById(im.getProductId());
            System.out.println("đay");
            BigInteger quantity = im.getQuantity();
                product.setAvailable(product.getAvailable().add(quantity));
                Sku sku = new Sku();
                sku.setId(im.getSkuId()+"-"+importTransaction.getId());
                sku.setQuantity(quantity);
                sku.setProduct(product);
                sku = skuService.save(sku);
                List<Sku> skus =  product.getSkus();
                skus.add(sku);
                productService.save(product);
                BigDecimal importPrice = im.getImportPrice();
                BigDecimal subtotal = importPrice.multiply(new BigDecimal(quantity));
                ImportItem importItem = new ImportItem(new ImportItemId(importTransaction.getId(),container.getId(),product.getId()),quantity,importPrice,sku,importTransaction,container,product,subtotal);
                importItems.add(importItem);
                importItemRepository.save(importItem);
                totalPayment = totalPayment.add(subtotal);
        }
        importTransaction.setImportItems(importItems);
        importTransaction.setTotalPayment(totalPayment);
        importTransactionRepository.save(importTransaction);
        return true;
    }
}
