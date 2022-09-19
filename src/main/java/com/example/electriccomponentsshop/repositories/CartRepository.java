package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Cart;
import com.example.electriccomponentsshop.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "select * from cart where account_id = :accId", nativeQuery = true)
    Cart findByAccountId(int accId);

}
