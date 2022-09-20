package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.CartItemDTO;
import com.example.electriccomponentsshop.entities.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartItemService {

    CartItemDTO convertToDTO(CartItem cartItem);

    List<CartItemDTO> getCartItems (int accId);

    boolean addToCart(int accountId, int productId, BigDecimal quantity);

    boolean removeCartItem (int accountId, int productId);

    boolean updateCartItem (int accountId, int productId, BigDecimal quantity);

}
