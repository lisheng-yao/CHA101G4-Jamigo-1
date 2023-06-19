package com.jamigo.shop.platform_order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @Column(nullable = false, length = 20)
    private String buyerName;

    @Column(nullable = false, length = 10)
    private String buyerPhone;

    @Column(nullable = false, length = 40)
    private String buyerEmail;

    @Column(nullable = false)
    private Byte paymentMethod;

    @Column(nullable = false)
    private Byte pickupMethod;

    @Column(length = 10)
    private String deliveryCountry;

    @Column(length = 100)
    private String deliveryAddress;

    @Column(nullable = false)
    private Byte invoiceMethod;

    @Column(length = 8)
    private String invoiceGui;

    @Column(nullable = false)
    private Integer totalPaid;

    @Column(nullable = false)
    private Integer totalCoupon = 0;

    @Column(nullable = false)
    private Integer totalPoints = 0;

    @Column(nullable = false)
    private Integer actuallyPaid;

    @Column(nullable = false)
    private Integer rewardPoints;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp orderTime = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false)
    private Byte platformOrderStat;

    @Column(nullable = false)
    private Byte paymentStat;
}