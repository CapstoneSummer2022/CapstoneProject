package com.example.electriccomponentsshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parent_category_id")
    private Category parentCategory;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parentCategory")
    @JsonIgnore
    private Set<Category> childCategories;

    @JsonIgnore
    private String path;

    public Category(String name){
        this.name=name;
    }

}
