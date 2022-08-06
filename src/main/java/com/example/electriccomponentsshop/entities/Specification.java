package com.example.electriccomponentsshop.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Specification")
public class Specification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specificationId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "specification")
    List<SpecificationValue> specificationValues = new ArrayList<>();

}
