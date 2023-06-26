package com.jamigo.shop.platform_order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatformOrderDetailDTO {

    private String counterName;

    private Byte disbursementStat;

    private Integer productNo;

    private String productName;

    private Integer productPrice;

    private Integer amount;

    private Byte orderDetailStat;

    public PlatformOrderDetailDTO(String counterName, Byte disbursementStat, Integer productNo, String productName, Integer productPrice, Integer amount, Byte orderDetailStat) {
        this.counterName = counterName;
        this.disbursementStat = disbursementStat;
        this.productNo = productNo;
        this.productName = productName;
        this.productPrice = productPrice;
        this.amount = amount;
        this.orderDetailStat = orderDetailStat;
    }
}
