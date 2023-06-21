package com.jamigo.shop.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProductDTO {
    private Integer counterNo;
    private Integer productCategory;
    private String productName;
    private Integer productPrice;
    private String productInfo;
    private Boolean productStat;
}
