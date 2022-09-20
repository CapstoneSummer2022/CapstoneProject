package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Supplier", uniqueConstraints = @UniqueConstraint(columnNames = {"name","phone"}))
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    private String phone;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "productSupplier")
    List<Product> products = new ArrayList<>();
    private Integer active;
}
