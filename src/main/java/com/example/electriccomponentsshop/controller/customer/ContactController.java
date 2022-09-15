package com.example.electriccomponentsshop.controller.customer;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

public class ContactController {

    @GetMapping("/contact")
    public String getContact (ModelMap modelMap){
        //get store information

        return "customer/contact";
    }

}
