package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationValueId implements Serializable  {

    @ManyToOne
    @JoinColumn(name = "specification_id")
    private Specification specification;

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }


    @Transient
    private Product product;

    public Product getProduct() {
        return product;
    }

}
