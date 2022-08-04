package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="Product_Category", joinColumns =@JoinColumn(name= "product_id"),inverseJoinColumns =@JoinColumn( name="category_id") )
    private Set<Category> categories = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Cart cart;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private List<ExportPrice> exportPrices = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private List<Feedback> feedbackList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private List<SpecificationValue> specificationValues = new ArrayList<>();

}
