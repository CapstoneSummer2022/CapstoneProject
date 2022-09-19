package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.CartItem;
import com.example.electriccomponentsshop.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.SpelQueryContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

    @Query(value = "select * from cart_item ci join cart c on ci.cart_id = c.id where c.account_id = :accId", nativeQuery = true)
    List<CartItem> getCartItems (int accId);

    @Query(value = "delete from cart_item where cart_id = :cartId", nativeQuery = true)
    void deleteAllCartItemByCartId (int cartId);

}
