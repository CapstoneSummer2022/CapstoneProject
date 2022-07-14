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
@Table(name ="ImportTransaction")
public class ImportTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "importTransaction")
    List<ProductPackage> productPackages = new ArrayList<>();


}
