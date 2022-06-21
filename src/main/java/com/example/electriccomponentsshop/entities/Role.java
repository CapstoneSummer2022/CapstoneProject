package com.example.electriccomponentsshop.entities;

import com.example.electriccomponentsshop.common.ERole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole roleName;


}
