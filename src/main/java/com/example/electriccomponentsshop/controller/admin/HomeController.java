package com.example.electriccomponentsshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/admin/home")
public class HomeController {
    @GetMapping("")
    public String viewHome(Model model){

        return "administrator/admin-home";

    }
}
