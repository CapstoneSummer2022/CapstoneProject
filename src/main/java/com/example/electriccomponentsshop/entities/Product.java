package com.example.electriccomponentsshop.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    private Date addedDate;
    @Column
    @ColumnDefault("0")
    private Integer available;
    @Column
    private int status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Product_Category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ExportPrice exportPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Feedback> feedbackList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<SpecificationValue> specificationValues = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier productSupplier;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productImport")
    private List<ImportItem> importItemsProduct = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Sku> skus = new ArrayList<>();
}
