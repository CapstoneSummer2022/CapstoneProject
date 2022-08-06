package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecificationValueId implements Serializable  {
   @Column(name="product_id")
   private Integer productId;
   @Column(name="specification_id")
   private Integer specificationId;







}
