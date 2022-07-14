package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="StoreInformation")
public class StoreInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String facebook;
    @Column
    private String instagram;
    @Column
    private String mail;
}
