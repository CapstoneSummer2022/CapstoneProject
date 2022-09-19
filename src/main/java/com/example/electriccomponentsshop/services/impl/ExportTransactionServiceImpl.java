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
        return modelMap.modelMapper().map(exportTransaction, ExportTransactionDto.class);
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
        System.out.println("size " + exportItemDtoList.size());
        for (ExportItemDto e: exportItemDtoList
             ) {
            Sku sku = skuService.getSkuById(e.getSkuId());
            sku.setQuantity(sku.getQuantity().subtract(e.getQuantity()));
            skuService.save(sku);
            System.out.println("dcmi");
            ExportItem exportItem = new ExportItem(new ExportItemId(exportTransaction.getId(),sku.getId()),e.getQuantity(),sku,exportTransaction);
            exportItemRepository.save(exportItem);
            exportItems.add(exportItem);
            System.out.println("dcmm");
        }
        order.setStatus(OrderEnum.DELIVERY.getName());
        orderService.save(order);
        exportTransaction.setExportItems(exportItems);
        exportTransactionRepository.save(exportTransaction);
    }



}
