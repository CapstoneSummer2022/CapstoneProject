package com.example.electriccomponentsshop.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany( mappedBy = "cart")
    private List<CartItem> cartItem;

    private Integer totalProduct;

    private Double totalPayment;

}
