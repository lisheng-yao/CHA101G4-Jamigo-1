package com.jamigo.shop.product_picture.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_picture")
public class ProductPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer productPicNo;

    @Column(nullable = false)
    private Integer productNo;

    private byte[] productPic;
}