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
    private Integer platformOrderNo;  // 平台訂單編號

    @Column(nullable = false)
    private Integer memberNo;  // 會員編號

    @Column(nullable = false, length = 20)
    private String buyerName;  // 訂購人姓名

    @Column(nullable = false, length = 10)
    private String buyerPhone;  // 訂購人手機號碼

    @Column(nullable = false, length = 40)
    private String buyerEmail;  // 訂購人Email

    @Column(nullable = false)
    private Byte paymentMethod;  // 付款方式

    @Column(nullable = false)
    private Byte pickupMethod;  // 取貨方式

    @Column(length = 10)
    private String deliveryCountry;  // 宅配國家

    @Column(length = 100)
    private String deliveryAddress;  // 宅配住址

    @Column(nullable = false)
    private Byte invoiceMethod;  // 開立發票方式

    @Column(length = 8)
    private String invoiceGui;  // 發票統一編號

    @Column(nullable = false)
    private Integer totalPaid;  // 訂單原總金額

    @Column(nullable = false)
    private Integer totalCoupon;  // 折價券折價總和

    @Column(nullable = false)
    private Integer totalPoints;  // 點數折抵總和

    @Column(nullable = false)
    private Integer actuallyPaid;  // 訂單實付金額

    @Column(nullable = false)
    private Integer rewardPoints;  // 訂單回饋點數

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp orderTime;  // 下單時間

    @Column(nullable = false)
    private Byte platformOrderStat;  // 訂單狀態

    @Column(nullable = false)
    private Byte paymentStat;  // 會員付款狀態
}