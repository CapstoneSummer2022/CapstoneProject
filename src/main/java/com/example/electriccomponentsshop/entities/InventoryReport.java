package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "InventoryReport")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryReport {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer month;
    private Integer year;
}
