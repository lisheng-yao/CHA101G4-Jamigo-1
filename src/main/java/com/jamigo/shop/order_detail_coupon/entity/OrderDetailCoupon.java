package com.jamigo.shop.order_detail_coupon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_detail_coupon")
public class OrderDetailCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer orderDetailCouponNo;

    private Integer counterOrderNo;

    private Integer platformOrderNo;

}