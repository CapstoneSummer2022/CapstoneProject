package com.example.electriccomponentsshop.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@ToString
@Table(name = "Product", uniqueConstraints = {@UniqueConstraint(columnNames = "Email")})
public class Product {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;


}
