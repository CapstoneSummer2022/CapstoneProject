package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.common.OrderEnum;
import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.ExportItemDto;
import com.example.electriccomponentsshop.dto.ExportTransactionDto;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.ExportItemRepository;
import com.example.electriccomponentsshop.repositories.ExportTransactionRepository;
import com.example.electriccomponentsshop.services.ExportTransactionService;
import com.example.electriccomponentsshop.services.OrderService;
import com.example.electriccomponentsshop.services.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExportTransactionServiceImpl implements ExportTransactionService {
    @Autowired
    ExportTransactionRepository exportTransactionRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    SkuService skuService;
    @Autowired
    ModelMap modelMap;
    @Autowired
    ExportItemRepository exportItemRepository;
    @Override
    public ExportTransactionDto convertToDto(ExportTransaction exportTransaction){
        System.out.println(exportTransaction.getId()+"ds");
        ExportTransactionDto exportTransactionDto = modelMap.modelMapper().map(exportTransaction, ExportTransactionDto.class);
        exportTransactionDto.setExportDate(exportTransaction.getExportDate().toString());
        exportTransactionDto.setExportPerson(exportTransactionDto.getExportPerson());
        exportTransactionDto.setReceivedPerson(exportTransactionDto.getReceivedPerson());
        return exportTransactionDto;
    }
    @Override
    public ExportTransactionDto getDtoById(String id){
        return convertToDto(getById(id));
    }

    @Override
    public ExportTransaction getById(String id){
        try{
            int eId = Integer.parseInt(id);
            Optional<ExportTransaction> exportTransactionOptional = exportTransactionRepository.findById(eId);
            if(exportTransactionOptional.isPresent()){
                return exportTransactionOptional.get();
            }else throw  new NoSuchElementException("Không có giao dịch xuất này");
        }catch (NumberFormatException e){
            throw  new NoSuchElementException("Không tìm thấy giao dịch xuất này");
        }
    }
    @Override
    public List<ExportTransactionDto>  findAll(){
        List<ExportTransaction> exportTransactionList = exportTransactionRepository.findAll();
        System.out.println(exportTransactionList.size()+"đây là ");
        if(exportTransactionList.isEmpty()){
            throw new NoSuchElementException("Không có dữ liệu");
        }else{
            return exportTransactionList.stream().map(this::convertToDto).collect(Collectors.toList());
        }

    }
    @Override
    public void addExportTransaction(ExportTransactionDto exportTransactionDto) throws NoSuchElementException{
        ExportTransaction exportTransaction = new ExportTransaction();
        exportTransaction.setExportPerson(exportTransactionDto.getExportPerson());
        exportTransaction.setReceivedPerson(exportTransactionDto.getReceivedPerson());
        exportTransaction.setDescription(exportTransactionDto.getDescription());
        Order order = orderService.getById(exportTransactionDto.getOrderId());
        exportTransaction.setOrder(order);
        exportTransaction.setExportDate(Date.valueOf(exportTransactionDto.getExportDate()));
        exportTransaction =  exportTransactionRepository.save(exportTransaction);
        List<ExportItem> exportItems = new ArrayList<>();
         List<ExportItemDto> exportItemDtoList = exportTransactionDto.getExportItems();
        for (ExportItemDto e: exportItemDtoList
             ) {
            System.out.println(e.getSkuId()+"hoangx");
            Sku sku = skuService.getSkuById(e.getSkuId());

            Product product = sku.getProduct();
            BigInteger quantity = e.getQuantity();
            sku.setQuantity(sku.getQuantity().subtract(quantity.multiply(product.getUnit())));
            skuService.save(sku);
            ExportItem exportItem = new ExportItem(new ExportItemId(exportTransaction.getId(),sku.getId()),quantity,sku,exportTransaction);
            exportItemRepository.save(exportItem);
            exportItems.add(exportItem);
        }
        order.setStatus(OrderEnum.DELIVERY.getName());
        orderService.save(order);
        exportTransaction.setExportItems(exportItems);
        exportTransactionRepository.save(exportTransaction);
    }

    @Override
    public void updateExportTransaction(ExportTransactionDto exportTransactionDto) throws NoSuchElementException{
        ExportTransaction exportTransaction = getById(exportTransactionDto.getId());
        exportTransaction.setExportPerson(exportTransactionDto.getExportPerson());
        exportTransaction.setReceivedPerson(exportTransactionDto.getReceivedPerson());
        exportTransaction.setDescription(exportTransactionDto.getDescription());
        Order order = orderService.getById(exportTransactionDto.getOrderId());
        exportTransaction.setOrder(order);
        exportTransaction.setExportDate(Date.valueOf(exportTransactionDto.getExportDate()));
        exportTransaction =  exportTransactionRepository.save(exportTransaction);
        List<ExportItem> exportItems = new ArrayList<>();
        List<ExportItemDto> exportItemDtoList = exportTransactionDto.getExportItems();
        exportItemRepository.deleteExportItemsByExportTransactionId(exportTransaction.getId());
        for (ExportItem e: exportTransaction.getExportItems()
        ) {
            Sku sku = e.getSku();
            Product product = sku.getProduct();
            BigInteger quantity = e.getQuantity();
            sku.setQuantity(sku.getQuantity().add(quantity.multiply(product.getUnit())));
            skuService.save(sku);
        }
        for (ExportItemDto e: exportItemDtoList
        ) {
            Sku sku = skuService.getSkuById(e.getSkuId());
            Product product = sku.getProduct();
            BigInteger quantity = e.getQuantity();
            sku.setQuantity(sku.getQuantity().subtract(quantity.multiply(product.getUnit())));
            skuService.save(sku);
            ExportItem exportItem = new ExportItem(new ExportItemId(exportTransaction.getId(),sku.getId()),quantity,sku,exportTransaction);
            exportItemRepository.save(exportItem);
            exportItems.add(exportItem);
        }
        exportTransaction.setExportItems(exportItems);
        exportTransactionRepository.save(exportTransaction);
    }

}
