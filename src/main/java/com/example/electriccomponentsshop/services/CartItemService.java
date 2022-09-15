package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.CartDTO;
import com.example.electriccomponentsshop.entities.CartItem;

import java.util.List;

public interface CartItemService {

    CartDTO convertToDto(CartItem cart);

    List<CartDTO> getCartByAccountId(int accId);

    CartItem getCartItem (int accId, int proId);

    boolean addToCart (int accId, int proId, int quantity);

    boolean updateCart (int accId, int proId, int quantity);

    boolean removeCartItem (int accId, int proId);

}
