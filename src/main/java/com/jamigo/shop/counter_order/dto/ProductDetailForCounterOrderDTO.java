package com.jamigo.shop.counter_order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailForCounterOrderDTO {

    private Integer productNo;
    private String productName;
    private Integer productPrice;
    private Integer amount;
    private Byte orderDetailStat;

    private String evalContent;
    private Integer evalScore;
}
