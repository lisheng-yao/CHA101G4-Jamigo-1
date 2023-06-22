package com.jamigo.shop.product.dto;

import com.jamigo.shop.product.entity.ProductCategory;
import com.jamigo.shop.product.entity.ProductPic;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductPageDTO {

    private Integer productNo;
    private ProductCategory productCategory;
    private Integer counterNo;
    private Integer productPrice;
    private String productInfo;
    private Boolean productStat;
    private Integer productSaleNum;
    private Integer reportNumber;
    private String productName;
    private Integer evalTotalPeople;
    private Integer evalTotalScore;
    private List<ProductPic> productPics;
    private String counterName;

}
