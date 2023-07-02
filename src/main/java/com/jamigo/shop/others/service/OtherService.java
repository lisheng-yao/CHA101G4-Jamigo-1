package com.jamigo.shop.others.service;

import com.jamigo.shop.others.dto.ProductForMainPageDTO;

import java.util.List;

public interface OtherService {

    List<ProductForMainPageDTO> getRecommendation();

    List<ProductForMainPageDTO> getCounterRecommendation(Integer counterNo);
}
