package com.jamigo.shop.product.entity;

import com.jamigo.counter.counter.entity.Counter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "counterNo", nullable = false)
    private Counter counter;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false, length = 1000)
    private String productInfo;

    @Column(nullable = false)
    private Byte productStat;

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

}