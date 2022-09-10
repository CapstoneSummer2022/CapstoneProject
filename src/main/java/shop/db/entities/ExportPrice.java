package shop.db.entities;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ExportPrice")
public class ExportPrice {
    @Id
    @Column(name = "product_id")
    private Integer id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
    @Column
    private Double wholeSalePrice;
    @Column
    private Double retailPrice;
    @Column
    @UpdateTimestamp
    private Date updatedDate;

}