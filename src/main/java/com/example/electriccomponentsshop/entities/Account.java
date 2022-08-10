package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    private String name;
    private String phone;
    @Column
    private String password;
    private String detailLocation;
    private String gender;
    private Date birthDate;
    private Boolean status;
    public Set<Role> getRoles() {
        return roles;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private RefreshToken refreshToken;
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    List<Feedback> feedbackList =new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pCode")
    private Province provinceAccount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dCode")
    private District districtAccount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="wCode")
    private Ward wardAccount;
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Account(String email, String password,Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public String getDob(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.birthDate);
    }


}
