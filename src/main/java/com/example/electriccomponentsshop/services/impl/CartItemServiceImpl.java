package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CartItemDTO;
import com.example.electriccomponentsshop.entities.Cart;
import com.example.electriccomponentsshop.entities.CartItem;
import com.example.electriccomponentsshop.entities.CartItemId;
import com.example.electriccomponentsshop.entities.Product;
import com.example.electriccomponentsshop.repositories.CartItemRepository;
import com.example.electriccomponentsshop.repositories.CartRepository;
import com.example.electriccomponentsshop.services.CartItemService;
import com.example.electriccomponentsshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    ModelMap modelMap;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartItemDTO convertToDTO(CartItem cartItem) {
        return modelMap.modelMapper().map(cartItem, CartItemDTO.class);
    }

    @Override
    public List<CartItemDTO> getCartItems(int accId) {
        List<CartItem> cartItems = cartItemRepository.getCartItems(accId);

        return cartItems.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean addToCart(int accountId, int productId, BigDecimal quantity) {
        Cart cart = cartRepository.findByAccountId(accountId);
        CartItemId cartItemId = new CartItemId(productId, cart.getId());
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

        CartItem cartItem;
        BigDecimal subTotal;

        if (!cartItemOptional.isPresent()) {
            Product product = productService.getById(String.valueOf(productId));
            subTotal = quantity.multiply(product.getExportPrice().getRetailPrice());

            cartItem = new CartItem(new CartItemId(accountId, productId), product, quantity, subTotal, cart);
        } else {
            cartItem = cartItemOptional.get();
            quantity = quantity.add(cartItem.getQuantity());
            subTotal = quantity.multiply(cartItem.getProduct().getExportPrice().getRetailPrice());

            cartItem.setQuantity(quantity);
            cartItem.setSubTotal(subTotal);
        }

        return cartItemRepository.save(cartItem) != null;
    }

    @Override
    public boolean removeCartItem(int accountId, int productId) {
        Cart cart = cartRepository.findByAccountId(accountId);

        CartItemId cartItemId = new CartItemId(productId, cart.getId());

        cartItemRepository.deleteById(cartItemId);

        return cartItemRepository.findById(cartItemId).isPresent();
    }

    @Override
    public boolean updateCartItem(int accountId, int productId, BigDecimal quantity) {
        Cart cart = cartRepository.findByAccountId(accountId);

        CartItemId cartItemId = new CartItemId(productId, cart.getId());
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();

        BigDecimal subTotal = quantity.multiply(cartItem.getProduct().getExportPrice().getRetailPrice());

        cartItem.setQuantity(quantity);
        cartItem.setSubTotal(subTotal);

        return cartItemRepository.save(cartItem) != null;
    }

}
