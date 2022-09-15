package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @EmbeddedId
    private CartItemId cartItemId;

    @ManyToOne(fetch =FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;

    private Integer quantity;

    private Double subTotal;

    @ManyToOne(fetch =FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name="cart_id")
    private Cart cart;

}
