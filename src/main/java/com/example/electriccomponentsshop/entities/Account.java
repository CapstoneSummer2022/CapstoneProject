package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Accounts", uniqueConstraints = {@UniqueConstraint(columnNames = "Email")})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String email;
    @Column
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private UserInformation userInformation;
    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private RefreshToken refreshToken;
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    List<Feedback> feedbackList =new ArrayList<>();
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }



}
