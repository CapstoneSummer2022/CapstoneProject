package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer accountId;

    @OneToMany( mappedBy = "cart")
    private List<CartItem> cartItems;
    private BigInteger totalProduct;
    private BigDecimal totalPayment;
    public Cart (Integer accountId, List<CartItem> items) {
        this.accountId = accountId;
        this.cartItems = items;
    }

}
