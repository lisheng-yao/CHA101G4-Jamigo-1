package com.jamigo.shop.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product_category")
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productCatNo")
    private int productCatNo;

    @Column(name = "productCatName", length = 40)
    private String productCatName;
}
