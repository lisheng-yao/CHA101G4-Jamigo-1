package com.jamigo.promotion.PromotionType.controller;

import com.jamigo.promotion.PromotionType.Entity.Promotion;
import com.jamigo.promotion.PromotionType.Service.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class GetAllPromotionType {

    @Autowired
    private PromotionTypeService promotionTypeService;

    @GetMapping("Promotion/GetAllPromotionType")
    public List<Promotion> findAll() {
        return promotionTypeService.findAll();
    }
}
