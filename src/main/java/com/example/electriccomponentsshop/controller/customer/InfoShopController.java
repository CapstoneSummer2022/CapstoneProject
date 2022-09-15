package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.entities.StoreInformation;
import com.example.electriccomponentsshop.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoShopController {

    @Autowired
    StoreRepository storeRepository;

    @GetMapping(value = "/store-info")
    public List<StoreInformation> getStoreInfo () {
        return storeRepository.findAll();
    }
}
