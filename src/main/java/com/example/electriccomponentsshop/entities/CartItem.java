package com.example.electriccomponentsshop.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @EmbeddedId
    private CartItemId cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;

    private BigDecimal quantity;

    private BigDecimal subTotal;

    @ManyToOne(fetch =FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name="cart_id")
    private Cart cart;

    public CartItem (Product product, BigDecimal quantity, BigDecimal subTotal, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.cart = cart;
    }

}
