package shop.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Sku")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(mappedBy = "sku")
    private ImportItem importItem;
    @CreationTimestamp
    private Date createdDate;
    private Integer quantity;
}