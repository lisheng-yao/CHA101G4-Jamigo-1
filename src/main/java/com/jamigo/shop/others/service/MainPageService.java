package com.jamigo.shop.others.service;

import com.jamigo.shop.others.dto.ProductForMainPageDTO;
import com.jamigo.shop.product.entity.ProductCategory;

import java.util.List;

public interface MainPageService {

    List<ProductForMainPageDTO> getRecommendation();

    List<ProductForMainPageDTO> getCounterRecommendation(Integer counterNo);

    List<ProductForMainPageDTO> getBestSelling();

    List<ProductCategory> getAllProductCategory();

    ProductCategory getProductCategoryById(Integer productCatNo);

    List<ProductForMainPageDTO> getProductsByCategory(Integer productCatNo, Integer pageNum, Integer orderBy);

    Integer getProductAmountByCategory(Integer productCatNo);

    List<ProductForMainPageDTO> getProductsByKeyword(String keyword, Integer orderBy);

    int getLCSLength(String str1, String str2);
}
