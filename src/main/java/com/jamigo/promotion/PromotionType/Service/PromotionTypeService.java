package com.jamigo.promotion.PromotionType.Service;

import com.jamigo.promotion.PromotionType.Entity.Promotion;

import java.util.List;

public interface PromotionTypeService {
    Promotion add(Promotion promotion);


    Promotion edit(Promotion promotion);

    List<Promotion> findAll();

    boolean remove(String promotionName);

}
