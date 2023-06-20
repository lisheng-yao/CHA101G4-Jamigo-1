package com.jamigo.promotion.PromotionType.Dao;

import com.jamigo.promotion.PromotionType.Entity.Promotion;

import java.util.List;

public interface PromotionTypeDao {
    List<Promotion> selectAll();
    int update(Promotion promotion);
    int deleteByPK(String promotionName);
    int insert(Promotion promotion);

    Promotion selectById(String promotionName);
}
