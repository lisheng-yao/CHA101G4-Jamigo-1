package com.jamigo.shop.platform_order.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 用於結帳時，自動帶入購物車資料的 DTO
 */
@Getter
@Setter
public class CartForCheckoutDTO {

    // 櫃位編號
    private Integer counterNo;
    // 櫃位名稱
    private String counterName;
    // 商品編號
    private Integer productNo;
    // 商品名稱
    private String productName;
    // 商品價格
    private Integer productPrice;
    // 商品購買數量
    private Integer amount;

    public CartForCheckoutDTO(Integer counterNo, String counterName, Integer productNo, String productName, Integer productPrice, Integer amount) {
        this.counterNo = counterNo;
        this.counterName = counterName;
        this.productNo = productNo;
        this.productName = productName;
        this.productPrice = productPrice;
        this.amount = amount;
    }
}
