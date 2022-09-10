package shop.db.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Supplier", uniqueConstraints = @UniqueConstraint(columnNames = {"Mail","Phone"}))
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String mail;
    @Column
    private String address;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "supplier")
    List<ProductPackage> productPackages = new ArrayList<>();


}
