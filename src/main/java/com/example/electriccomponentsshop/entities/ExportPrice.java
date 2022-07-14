package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ExportPrice")
public class ExportPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Double wholeSalePrice;
    @Column
    private Double retailPrice;
    @Column
    private Date updatedDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;
}
