package com.jamigo.shop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer productNo;

    @ManyToOne
    @JoinColumn(name = "productCatNo", referencedColumnName = "productCatNo", nullable = false)
    private ProductCategory productCategory;

//    @ManyToOne
//    @JoinColumn(name = "counterNo", nullable = false)
    @Column(nullable = false)
    private Integer counterNo;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false, length = 1000)
    private String productInfo;

    @Column(nullable = false)
    private Boolean productStat;

    @Column(nullable = false, insertable = false)
    private Integer productSaleNum;

    @Column(nullable = false, insertable = false)
    private Integer reportNumber;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false, insertable = false)
    private Integer evalTotalPeople;

    @Column(nullable = false, insertable = false)
    private Integer evalTotalScore;

   // @JsonManagedReference
    //@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @OneToMany
    @JoinColumn(name="productNo")
    private List<ProductPic> productPics;

}