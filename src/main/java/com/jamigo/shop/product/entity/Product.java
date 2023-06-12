package com.jamigo.shop.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer productNo;

    @Column(nullable = false)
    private Integer productCatNo;

    @Column(nullable = false)
    private Integer counterNo;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false, length = 1000)
    private String productInfo;

    @Column(nullable = false)
    private Byte productStat;

    @Column(nullable = false)
    private Integer productSaleNum;

    @Column(nullable = false)
    private Integer reportNumber;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false)
    private Integer evalTotalPeople;

    @Column(nullable = false)
    private Integer evalTotalScore;

}