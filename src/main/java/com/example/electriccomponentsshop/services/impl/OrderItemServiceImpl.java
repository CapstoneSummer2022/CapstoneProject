package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.OrderItemDto;
import com.example.electriccomponentsshop.entities.OrderItem;
import com.example.electriccomponentsshop.repositories.OrderItemRepository;
import com.example.electriccomponentsshop.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public OrderItemDto convertToDto(OrderItem orderItem){
        System.out.println("gihi");
        OrderItemDto orderItemDto =modelMap.modelMapper().map(orderItem,OrderItemDto.class);
        System.out.println("ghi" + orderItem.getProduct().getName());
        orderItemDto.setProductName(orderItem.getProduct().getName());

        return orderItemDto;
    }
    @Override
    public List<OrderItemDto> findAll() {
        return orderItemRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<OrderItemDto> findByOrderId(Integer id) {
        return orderItemRepository.findByOrderId(id).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteOrderItemsByOrderId(Integer orderId) {
        orderItemRepository.deleteOrderItemsByOrderId(orderId);
    }

    @Override
    public <S extends OrderItem> S save(S entity) {
        return orderItemRepository.save(entity);
    }
}
