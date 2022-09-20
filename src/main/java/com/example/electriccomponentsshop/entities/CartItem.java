package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

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

    private BigInteger quantity;

    private BigDecimal subTotal;

    @ManyToOne(fetch =FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name="cart_id")
    private Cart cart;
}
