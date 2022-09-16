package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SalesItem")
public class SalesItem {
    @EmbeddedId
    private SalesItemId salesItemId;

    private Integer sales;
    private Integer valueTo;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("specificationId")
    @JoinColumn(name="specification_id")
    private Specification specification;
}
