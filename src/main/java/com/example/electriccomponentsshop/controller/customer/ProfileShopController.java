package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ProfileShopController {

    @Autowired
    AccountService accountService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    DistrictService districtService;

    @Autowired
    WardService wardService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/profile")
    public String getProfile (Authentication authentication, ModelMap map) {
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();
        AccountDTO account = accountService.findById(accountDetail.getId());
        List<ProvinceDTO> provinces = provinceService.findAll();

        if (account.getDistrictName() != null && account.getWardName() != null) {
            List<DistrictDTO> districts = districtService.findByProvinceName(account.getProvinceName());
            List<WardDTO> wards = wardService.findByDistrictName(account.getDistrictName());

            map.addAttribute("wards", wards);
            map.addAttribute("districts", districts);
        }

        map.addAttribute("categories", categories);
        map.addAttribute("account", account);
        map.addAttribute("provinces", provinces);

        return "customer/profile";
    }

    @PostMapping(value = "/profile")
    public String updateProfile (@RequestParam Map<String, String> profileInfo) {


        accountService.updateAccount(null, 1);

        return "redirect:/profile";
    }
}
