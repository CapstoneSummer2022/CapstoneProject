package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name="Sku")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sku {
    @Id
    private String id ;
    @OneToOne(mappedBy = "sku")
    private ImportItem importItem;
    private BigInteger quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="productId")
    private Product product;
}
