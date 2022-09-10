package com.example.electriccomponentsshop.controller;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.SupplierDTO;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.OrderItem;
import com.example.electriccomponentsshop.entities.OrderItemId;
import com.example.electriccomponentsshop.entities.Supplier;
import com.example.electriccomponentsshop.repositories.*;
import com.example.electriccomponentsshop.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@CrossOrigin
@RequestMapping("/")
public class TestController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private ModelMap modelMap;
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    OrderItemService orderItemRepository;
    @Autowired
    OrderItemRepository itemRepository;

    @PostMapping("/suppliers/add")
    public ResponseEntity<?> addNewSupplier(@Validated @RequestBody SupplierDTO supplierDTO)throws  Exception{
        Supplier s = convertToEntity(supplierDTO);
        supplierRepository.save(s);
        return ResponseEntity.ok(s);

    }
    @GetMapping("/error23")
    public String error(){
        return "signin1";
    }
    @GetMapping("/error-401")
    public String error401(){

        return "sos";
    }
    private SupplierDTO convertToDto(Supplier s){
        SupplierDTO supplierDTO = modelMap.modelMapper().map(s,SupplierDTO.class);
        return supplierDTO;
    }
    private Supplier convertToEntity(SupplierDTO supplierDTO){
        Supplier s =  modelMap.modelMapper().map(supplierDTO,Supplier.class);
        return s;
    }


}

