package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Userinformation",uniqueConstraints = {@UniqueConstraint(columnNames = {"Email","Phone"})})
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @MapsId
    @JoinColumn(name ="account_id")
    private Account account;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String name;

}
