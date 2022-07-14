package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductPackage")
public class ProductPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Date importDate;
    @Column
    private Double importPrice;
    @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name="import_transaction_id")
    private ImportTransaction importTransaction;
    @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="supplier_id")
    private Supplier supplier;
}
