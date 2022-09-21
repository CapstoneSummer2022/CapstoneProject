package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.common.OrderEnum;
import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrderShopController {

    @Autowired
    CategoryService categoryService;

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

    @PostMapping(value = "/create-order")
    public String createOrder (@RequestParam Map<String, String> order, RedirectAttributes redirectAttributes) {
        int orderId = orderService.createOrderOnline(order);
        redirectAttributes.addFlashAttribute("orderId",orderId);
        return "redirect:/order";
    }

    @GetMapping(value = "/order")
    public String getAllOrder (Authentication authentication, ModelMap map) {
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();
        List<OrderDTO> orders = orderService.findAllOrderForCustomer(accountDetail.getId());

        map.addAttribute("categories", categories);
        map.addAttribute("orders", orders);

        return "customer/all-orders";
    }

    @GetMapping(value = "/order/{status}")
    public String getOrderByStatus (Authentication authentication, ModelMap map, @PathVariable String status) {
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();
        List<OrderDTO> orders = new ArrayList<>();
        String page = "";

        if (status.equals("waiting")) {
            orders = orderService.findOrderByStatusForCustomer(accountDetail.getId(), OrderEnum.PENDING.getName());
            page = "waiting-order";
        } else if (status.equals("shipping")) {
            orders = orderService.findOrderByStatusForCustomer(accountDetail.getId(), OrderEnum.DELIVERY.getName());
            page = "shipping-order";
        } else if (status.equals("received")) {
            orders = orderService.findOrderByStatusForCustomer(accountDetail.getId(), OrderEnum.DONE.getName());
            page = "received-order";
        } else if (status.equals("cancelled")) {
            orders = orderService.findOrderByStatusForCustomer(accountDetail.getId(), "Đã Hủy");
            page = "cancelled-order";
        }

        map.addAttribute("categories", categories);
        map.addAttribute("orders", orders);

        return "customer/"+page;
    }
}
