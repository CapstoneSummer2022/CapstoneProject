package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "OrderKind")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kindId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderKind")
    List<Order> orders = new ArrayList<>();

}
