package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.CartItem;
import com.example.electriccomponentsshop.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, CartItemId> {

    List<CartItem> findAllByAccountId(int accId);

    CartItem findByAccountIdAndProductId(int accId, int proId);

}
