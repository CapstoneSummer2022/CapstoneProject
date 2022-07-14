package com.example.electriccomponentsshop.controller;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.SupplierDTO;
import com.example.electriccomponentsshop.entities.AccountInformation;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.entities.Product;
import com.example.electriccomponentsshop.entities.Supplier;
import com.example.electriccomponentsshop.repositories.AccountInformationRepository;
import com.example.electriccomponentsshop.repositories.CategoryRepository;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import com.example.electriccomponentsshop.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountInformationRepository accountInformationRepository;
    @Autowired
    private ModelMap modelMap;
    @Autowired
    private SupplierRepository supplierRepository;

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateInformation(@PathVariable("id") int id, @Validated @RequestParam("phone") String phone, @Validated @RequestParam("name") String name, @Validated @RequestParam("email") String email) throws  Exception{
       Optional<AccountInformation> accountInformation= accountInformationRepository.findAccountInformationById(id);
       AccountInformation infor= new AccountInformation();
       if(accountInformation.isPresent()){
                infor = accountInformation.get();
                infor.setEmail(email);
                infor.setPhone(phone);
                infor.setName(name);
                accountInformationRepository.save(infor);
       }
       return ResponseEntity.ok(infor);
    }
    @PostMapping("/suppliers/add")
    public ResponseEntity<?> addNewSupplier(@Validated @RequestBody SupplierDTO supplierDTO)throws  Exception{
            Supplier s = convertToEntity(supplierDTO);
            supplierRepository.save(s);
            return ResponseEntity.ok(s);

    }
    @GetMapping("/category")
    public ResponseEntity<?> getParentCategories() throws Exception{


        ArrayList<Category> c = (ArrayList<Category>) categoryRepository.findCategoriesByParentCategoryIdIsNull();
        return ResponseEntity.ok(c);
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
