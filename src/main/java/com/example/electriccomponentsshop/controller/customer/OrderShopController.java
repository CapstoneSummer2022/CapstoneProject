package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.dto.*;
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
public class OrderShopController {

    @Autowired
    AccountService accountService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    DistrictService districtService;

    @Autowired
    WardService wardService;

    @GetMapping(value = "/create-order")
    public String createOrder (ModelMap map, Authentication authentication) {
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();
        List<CartItemDTO> cartItems = cartItemService.getCartItems(accountDetail.getId());
        List<ProvinceDTO> provinces = provinceService.findAll();

        AccountDTO account = accountService.findById(accountDetail.getId());
        if (account.getDistrictName() != null && account.getWardName() != null) {
            List<DistrictDTO> districts = districtService.findByProvinceName(account.getProvinceName());
            List<WardDTO> wards = wardService.findByDistrictName(account.getDistrictName());

            map.addAttribute("wards", wards);
            map.addAttribute("districts", districts);
        }

        map.addAttribute("account", account);
        map.addAttribute("provinces", provinces);
        map.addAttribute("cartItems", cartItems);

        return "customer/create-order";
    }

    //create order
    @PostMapping(value = "/create-order")
    public String createOrder (@RequestParam Map<String, String> order, ModelMap map) {
        boolean created = orderService.createOrderOnline(order);

        return "customer/order-result";
    }

    //notif status
}
