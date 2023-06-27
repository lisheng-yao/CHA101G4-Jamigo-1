package com.jamigo.shop.cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CouponInfoDTO {
    Integer memberCouponNo;
    Integer counterNo;  //用來判斷是否為櫃位折價券的標準
    Integer couponTypeNo;
    String couponTypeName;
    Integer couponPrice;
    Integer couponLowest;
}
