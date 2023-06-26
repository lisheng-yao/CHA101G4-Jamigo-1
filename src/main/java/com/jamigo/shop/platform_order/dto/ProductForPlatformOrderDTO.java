package com.jamigo.shop.platform_order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForPlatformOrderDTO {
    private Integer productNo;
    private String productName;
    private Integer productPrice;
    private Integer amount;
    private Byte orderDetailStat;
}
