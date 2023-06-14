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
    @EmbeddedId
    private CartId id;

    @ManyToOne
    @MapsId("productNo")
    @JoinColumn(name = "productNo")
    private Product product;

    @Column(nullable = false)
    private Integer amount;
}
