package shop.services.impl;


import com.example.electriccomponentsshop.dto.ImportTransactionDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.config.ModelMap;
import shop.db.dto.ImportItemDto;
import shop.db.entities.*;
import shop.db.repositories.ImportItemRepository;
import shop.db.repositories.ImportTransactionRepository;
import shop.db.repositories.WarehouseRepository;
import shop.services.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
        Warehouse warehouse  = warehouseService.getWarehouse(importTransactionDto.getWarehouseId());
        importTransaction.setWarehouseImport(warehouse);
        importTransaction = importTransactionRepository.save(importTransaction);
        List<ImportItem> importItems = new ArrayList<>();
        List<ImportItemDto> importItemDtos = importTransactionDto.getImportItems();
        System.out.println(importItemDtos.size() + "đây là");
        double totalPayment= 0.0;
        for (ImportItemDto im: importItemDtos
             ) {
            System.out.println("híadd");
                Container container = containerService.getContainerById(im.getContainerId());
            System.out.println(container.getRowIn()+ "cơ mà");
                Product product = productService.getById(im.getProductId());
            System.out.println("đay");
                Sku sku = new Sku();
                sku.setCode(im.getSkuCode()+"-"+importTransaction.getId());
            sku.setQuantity(im.getQuantity());
                sku = skuService.save(sku);
                Integer quantity = im.getQuantity();
                Integer importPrice = im.getImportPrice();
                double subtotal = quantity*importPrice;
                ImportItem importItem = new ImportItem(new ImportItemId(importTransaction.getId(),container.getId(),product.getId()),quantity,importPrice,sku,importTransaction,container,product);
                importItems.add(importItem);
                importItemRepository.save(importItem);
                totalPayment+=subtotal;
        }
        importTransaction.setImportItems(importItems);
        importTransaction.setTotalPayment(totalPayment);
        importTransactionRepository.save(importTransaction);
        return true;
    }
}
