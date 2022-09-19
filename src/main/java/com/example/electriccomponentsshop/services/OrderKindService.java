package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderKindDTO;
import com.example.electriccomponentsshop.entities.OrderKind;


import java.util.List;

public interface OrderKindService {
    OrderKindDTO convertToDto(OrderKind orderKind);

    OrderKind getById(String id);

    List<OrderKindDTO> findAll();
}
