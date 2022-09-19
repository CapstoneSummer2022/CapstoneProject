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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pCode")
    private Province provinceStore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dCode")
    private District districtStore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="wCode")
    private Ward wardStore;

    private String detailLocation;

    @Column
    private String phone;
    @Column
    private String facebook;
    @Column
    private String instagram;
    @Column
    private String email;

    public String getAddress() {
        return detailLocation + ", " + wardStore.getName() + ", " +
                districtStore.getName() + ", " + provinceStore.getName();
    }

    public String getAddressForMap() {

        return replaceSpace(detailLocation) + "," + replaceSpace(wardStore.getName()) + "," +
                replaceSpace(districtStore.getName()) + "," + replaceSpace(provinceStore.getName());
    }

    public String replaceSpace(String str) {
        return str.replace(" ", "+");
    }
}
