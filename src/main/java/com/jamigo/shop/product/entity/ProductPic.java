package com.jamigo.shop.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_picture")
public class ProductPic {

    //用來給Product逆向參照用
    private Integer productNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productPicNo", insertable = false)
    private Integer productPicNo;

   //  @JsonBackReference
   //  @ManyToOne(fetch = FetchType.EAGER)
   //  @JoinColumn(name = "productNo")
   //  @JoinColumn(name = "productNo", referencedColumnName = "productNo", nullable = false)
   // private Product product;

    // @Lob
    @Column(name = "productPic")
    private byte[] productPic;

}