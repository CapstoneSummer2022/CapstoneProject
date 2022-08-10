package com.example.electriccomponentsshop.entities;

import com.example.electriccomponentsshop.common.ERole;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole roleName;

    public ERole getRoleName() {
        return roleName;
    }
}
