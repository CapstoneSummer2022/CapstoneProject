package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.OrderKindDTO;
import com.example.electriccomponentsshop.entities.OrderKind;
import com.example.electriccomponentsshop.repositories.OrderKindRepository;
import com.example.electriccomponentsshop.services.OrderKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderKindServiceImpl implements OrderKindService {
    @Autowired
    OrderKindRepository orderTypeRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public OrderKindDTO convertToDto(OrderKind orderKind){
        return modelMap.modelMapper().map(orderKind, OrderKindDTO.class);
    }
    @Override
    public OrderKind getById(String id){
        try{
            int tId = Integer.parseInt(id);
            Optional<OrderKind> orderTypeOptional = orderTypeRepository.findById(tId);
            if(orderTypeOptional.isPresent()){
                return orderTypeOptional.get();
            }else throw new NoSuchElementException("Không tìm thấy loại đơn này");
        }catch (NumberFormatException e){
            throw new NoSuchElementException("Không tìm thấy loại đơn này");
        }
    }
    @Override
    public List<OrderKindDTO> findAll() {
        List<OrderKind> orderKinds = orderTypeRepository.findAll();
        if(orderKinds.isEmpty()){
            throw new NoSuchElementException("Không có dữ liệu");
        }else
            return orderKinds.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
