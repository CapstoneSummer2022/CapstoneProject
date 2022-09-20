package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.dto.CartItemDTO;
import com.example.electriccomponentsshop.entities.Cart;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.repositories.CartRepository;
import com.example.electriccomponentsshop.services.AccountDetailImpl;
import com.example.electriccomponentsshop.services.AccountService;
import com.example.electriccomponentsshop.services.CartItemService;
import com.example.electriccomponentsshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemService cartItemService;

    @GetMapping(value = "")
    public String viewCart (ModelMap map, Authentication authentication) {
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();

        List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();
        List<CartItemDTO> cartItems = cartItemService.getCartItems(accountDetail.getId());

        map.addAttribute("cartItems", cartItems);
        map.addAttribute("categories", categories);

        return "customer/cart";
    }

    @PostMapping(value = "/add-to-cart")
    public String addToCart (@RequestParam int productId, @RequestParam BigDecimal quantity, ModelMap map, Authentication authentication) {

        try {
            AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();

            boolean added = cartItemService.addToCart(accountDetail.getId(), productId, quantity);

            System.out.println("thuong: " + added);

            if (added == true) {
                map.addAttribute("success", "Đã thêm sản phẩm vào giỏ");
            } else {
                map.addAttribute("error", "Không thể thêm sản phẩm vào giỏ. Hãy thử lại");
            }
        } catch (Exception e) {
            throw e;
        }

        return "redirect:/product/" + productId;
    }

    @RequestMapping(value = "/remove")
    public String removeProductFromCart (@RequestParam Integer prodId, Authentication authentication) {
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();

        cartItemService.removeCartItem(accountDetail.getId(), prodId);

        return "redirect:/cart";
    }

    @RequestMapping(value = "/update-cart")
    public String updateCart (@RequestParam int prodId, @RequestParam BigDecimal quantity, Authentication authentication) {
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();

        boolean updated = cartItemService.updateCartItem(accountDetail.getId(), prodId, quantity);

        return "redirect:/cart";
    }

    @GetMapping(value = "/get-numbers-product")
    @ResponseBody
    public String getNumbersOfProductInCart (Authentication authentication) {
        AccountDetailImpl accountDetail = null;

        if (authentication != null) {
            accountDetail = (AccountDetailImpl) authentication.getPrincipal();
        }

        int numbersOfProduct = 0;

        if (accountDetail != null) {
            Cart cart = cartRepository.findByAccountId(accountDetail.getId());
            numbersOfProduct = cart.getCartItems().size();
        }

        return String.valueOf(numbersOfProduct);
    }
}
