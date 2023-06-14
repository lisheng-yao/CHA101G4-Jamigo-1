package com.jamigo.shop.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartForCheckoutDTO {
    private String counterName;
    private Integer productNo;
    private String productName;
    private Integer productPrice;
    private Integer amount;

    public CartForCheckoutDTO(String counterName, Integer productNo, String productName, Integer productPrice, Integer amount) {
        this.counterName = counterName;
        this.productNo = productNo;
        this.productName = productName;
        this.productPrice = productPrice;
        this.amount = amount;
    }
}
