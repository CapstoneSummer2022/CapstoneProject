//package com.example.electriccomponentsshop.services.impl;
//
//import com.example.electriccomponentsshop.dto.CartDTO;
//import com.example.electriccomponentsshop.entities.Account;
//import com.example.electriccomponentsshop.entities.CartItem;
//import com.example.electriccomponentsshop.entities.CartItemId;
//import com.example.electriccomponentsshop.entities.Product;
//import com.example.electriccomponentsshop.repositories.*;
//import com.example.electriccomponentsshop.services.CartItemService;
//import com.example.electriccomponentsshop.services.ProductService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class CartItemServiceImpl implements CartItemService {
//
//    @Autowired
//    ModelMapper modelMap;
//
//    @Autowired
//    CartRepository cartRepository;
//
//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Override
//    public CartDTO convertToDto(CartItem cart) {
//        return modelMap.map(cart, CartDTO.class);
//    }
//
//    @Override
//    public List<CartDTO> getCartByAccountId(int accId) {
//        List<CartItem> carts = null;
//        return carts.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public CartItem getCartItem(int accId, int proId) {
//        return cartRepository.findByAccountId(accId);
//    }
//
//    @Override
//    public boolean addToCart(int accId, int proId, int quantity) {
//        CartItem item = getCartItem(accId, proId);
//        Account account = accountRepository.findById(accId).get();
//        Product product = productService.getById(String.valueOf(proId));
//
//        if (item != null) {
//            double subtotal = quantity*product.getExportPrice().getRetailPrice();
//            //item = new CartItem(new CartItemId(proId, accId), product, quantity, subtotal, account);
//
//            return cartRepository.save(item) != null;
//        } else {
//            item.setQuantity(item.getQuantity()+quantity);
//            item.setSubTotal(item.getQuantity()*product.getExportPrice().getRetailPrice());
//
//            return cartRepository.save(item) != null;
//        }
//
//    }
//
//    @Override
//    public boolean updateCart(int accId, int proId, int quantity) {
//        CartItem item = getCartItem(accId, proId);
//        Product product = productService.getById(String.valueOf(proId));
//
//        item.setQuantity(quantity);
//        item.setSubTotal(quantity*product.getExportPrice().getRetailPrice());
//
//        return cartRepository.save(item) != null;
//    }
//
//    @Override
//    public boolean removeCartItem(int accId, int proId) {
//        CartItem item = getCartItem(accId, proId);
//        cartRepository.delete(item);
//
//        return getCartItem(accId, proId) != null;
//    }
//}
