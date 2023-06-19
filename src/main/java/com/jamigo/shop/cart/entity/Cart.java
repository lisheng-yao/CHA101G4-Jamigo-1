package com.jamigo.shop.cart.entity;

import com.jamigo.shop.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {
    // 複合主鍵 (見李偉銘老師 Hibernate 第 17 章)
    // 建法：建立一個購物車的主鍵類別
    @EmbeddedId
    private CartId id;

    @ManyToOne
    @MapsId("productNo")
    // 外來鍵：productNo，參照到表格：product
    // 現在要把它 join 在一起
    @JoinColumn(name = "productNo")
    private Product product;

    // 商品購買數量
    @Column(nullable = false)
    private Integer amount;
}
