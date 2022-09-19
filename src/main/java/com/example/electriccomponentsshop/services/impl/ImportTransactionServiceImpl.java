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
    public ImportTransaction getImportTransactionById(String id){
        try{
            int importId = Integer.parseInt(id);
            Optional<ImportTransaction> importTransaction = importTransactionRepository.findById(importId);
            if(importTransaction.isPresent()){
                return importTransaction.get();
            }
            else throw new NoSuchElementException("Không tìm thấy giao dịch nhập này");

        }catch (NumberFormatException e){
            throw  new NoSuchElementException("Không tìm thấy giao dịch nhập này");
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
    @Override
    public boolean addImportTransaction(ImportTransactionDto importTransactionDto) throws NoSuchElementException{
        ImportTransaction importTransaction= new ImportTransaction();
        importTransaction.setImportDate(Date.valueOf(importTransactionDto.getImportDate()));
        Supplier supplier = supplierService.getBySupplierId(importTransactionDto.getSupplierId());
        importTransaction.setSupplier(supplier);

        importTransaction = importTransactionRepository.save(importTransaction);
        List<ImportItem> importItems = new ArrayList<>();
        List<ImportItemDto> importItemDtos = importTransactionDto.getImportItems();
        BigDecimal totalPayment= new BigDecimal(0);
        for (ImportItemDto im: importItemDtos
             ) {
                Container container = containerService.getContainerById(im.getContainerId());
                Product product = productService.getById(im.getProductId());
                Sku sku = new Sku();
                sku = skuService.save(sku);
                BigDecimal quantity = im.getQuantity();
                BigDecimal importPrice = im.getImportPrice();
                BigDecimal subtotal = quantity.multiply(importPrice);
                importItems.add(new ImportItem(new ImportItemId(importTransaction.getId(),container.getId(),product.getId()),quantity,importPrice,sku,importTransaction,container,product));

        }
        importTransaction.setImportItems(importItems);

        Warehouse warehouse  = warehouseService.getWarehouse(importTransactionDto.getWarehouseId());
        importTransaction.setWarehouseImport(warehouse);
        importTransaction = importTransactionRepository.save(importTransaction);
        importItems = new ArrayList<>();
        importItemDtos = importTransactionDto.getImportItems();
        System.out.println(importItemDtos.size() + "đây là");
        totalPayment= new BigDecimal(0);
        for (ImportItemDto im: importItemDtos
             ) {
            System.out.println("híadd");
                Container container = containerService.getContainerById(im.getContainerId());
            System.out.println(container.getRowIn()+ "cơ mà");
                Product product = productService.getById(im.getProductId());
            System.out.println("đay");
                product.setAvailable(product.getAvailable().add(im.getQuantity()));

                Sku sku = new Sku();
                sku.setId(im.getSkuId()+"-"+importTransaction.getId());
                sku.setQuantity(im.getQuantity());
                sku.setProduct(product);
                sku = skuService.save(sku);
                List<Sku> skus =  product.getSkus();
                skus.add(sku);
                productService.save(product);
                BigDecimal quantity = im.getQuantity();
                BigDecimal importPrice = im.getImportPrice();
                BigDecimal subtotal = quantity.multiply(importPrice);
                ImportItem importItem = new ImportItem(new ImportItemId(importTransaction.getId(),container.getId(),product.getId()),quantity,importPrice,sku,importTransaction,container,product);
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
