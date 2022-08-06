package com.example.electriccomponentsshop.controller;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.SupplierDTO;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.Supplier;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.repositories.CategoryRepository;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import com.example.electriccomponentsshop.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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


    @PostMapping("/suppliers/add")
    public ResponseEntity<?> addNewSupplier(@Validated @RequestBody SupplierDTO supplierDTO)throws  Exception{
        Supplier s = convertToEntity(supplierDTO);
        supplierRepository.save(s);
        return ResponseEntity.ok(s);

    }
    @GetMapping("/error23")
    public String error(){
        System.out.println("dd");
        return "signin1";
    }
    @GetMapping("/home")
    public ResponseEntity<?> addNewSupplier()throws  Exception{

        ArrayList<Account> accounts=(ArrayList<Account>) accountRepository.findAllByRoleName("ROLE_CUSTOMER");
        return ResponseEntity.ok(accounts);


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

