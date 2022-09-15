package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Cart;
import com.example.electriccomponentsshop.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    CartItem findByAccountId(int accId);

}
