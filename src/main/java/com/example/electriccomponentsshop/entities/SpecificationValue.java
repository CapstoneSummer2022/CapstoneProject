package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SpecificationValue")
public class SpecificationValue {
   @EmbeddedId
    private SpecificationValueId specificationValueId;

    private String valueFrom;
    private String valueTo;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("specificationId")
    @JoinColumn(name="specification_id")
    private Specification specification;

}
