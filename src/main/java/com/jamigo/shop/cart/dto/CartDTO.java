package com.jamigo.shop.cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO {

    private Integer productNo;
    private Integer counterNo;
    private String counterName;
    private Integer quantity;
    private String productName;
    private Integer productPrice;

}
