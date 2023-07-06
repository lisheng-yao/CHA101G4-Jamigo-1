package com.jamigo.shop.platform_order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamigo.member.member_coupon.entity.MemberCouponId;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class CreatePlatformOrderDTO {

    private Integer platformOrderNo;  // 平台訂單編號

    private Integer memberNo;  // 會員編號

    private String buyerName;  // 訂購人姓名

    private String buyerPhone;  // 訂購人手機號碼

    private String buyerEmail;  // 訂購人Email

    private Byte paymentMethod;  // 付款方式

    private Byte pickupMethod;  // 取貨方式

    private String deliveryCountry;  // 宅配國家

    private String deliveryAddress;  // 宅配住址

    private Byte invoiceMethod;  // 開立發票方式

    private String invoiceGui;  // 發票統一編號

//    private Integer totalPaid;  // 訂單原總金額

    private List<MemberCouponId> memberCouponIdList;

    private Integer totalPoints;  // 點數折抵總和

//    private Integer actuallyPaid;  // 訂單實付金額

//    private Integer rewardPoints;  // 訂單回饋點數

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp orderTime;  // 下單時間

    private Byte platformOrderStat;  // 訂單狀態

    private Byte paymentStat;  // 會員付款狀態

}
