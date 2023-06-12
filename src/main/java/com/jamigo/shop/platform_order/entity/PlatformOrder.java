package com.jamigo.shop.platform_order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "platform_order")
public class PlatformOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer platformOrderNo;

    @Column(nullable = false)
    private Integer memberNo;

    @Column(nullable = false)
    private Instant orderTime;

    @Column(nullable = false)
    private Integer orderTotal;

    private Integer orderCouponTotal;

    private Integer orderPointsTotal;

    @Column(nullable = false)
    private Integer orderSalePrice;

    @Column(nullable = false)
    private Byte orderStat;

    @Column(nullable = false)
    private Byte shipStat;

    @Column(nullable = false)
    private Byte paymentStat;

    @Column(nullable = false)
    private Byte paymentMethod;

    @Column(length = 19)
    private String creditCard;

    @Column(length = 16)
    private String remitAccount;

    private Integer pointUsedOrder;

    private Integer orderRewardPoints;

    @Column(length = 100)
    private String orderAddress;
}