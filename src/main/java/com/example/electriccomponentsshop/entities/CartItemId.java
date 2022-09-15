package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemId implements Serializable {

    private int productId;

    private int cartId;

}
