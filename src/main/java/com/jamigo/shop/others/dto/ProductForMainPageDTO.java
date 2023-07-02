package com.jamigo.shop.others.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForMainPageDTO {

    private Integer productNo;
    private String productName;
    private Integer productPrice;
    private Integer counterNo;
    private String counterName;
    private Integer evalTotalPeople;
    private Integer evalTotalScore;
}
