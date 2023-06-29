package com.jamigo.promotion.PromotionType.Service.impl;

import com.jamigo.promotion.PromotionType.Dao.PromotionTypeDao;
import com.jamigo.promotion.PromotionType.Entity.Promotion;
import com.jamigo.promotion.PromotionType.Service.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionTypeServiceImpl implements PromotionTypeService {
    @Autowired
    private PromotionTypeDao Dao;

    @Override
    public Promotion add(Promotion promotion) {
        if (promotion.getPromotionName() == null) {
            promotion.setMessage("活動名稱未輸入");
            promotion.setSuccessful(false);
            return promotion;
        }
        if (Dao.selectById(promotion.getPromotionName()) != null) {
            promotion.setMessage("活動已存在");
            promotion.setSuccessful(false);
            return promotion;
        }
        final int resultCount = Dao.insert(promotion);
        if (resultCount < 1) {
            promotion.setMessage("新增錯誤");
            promotion.setSuccessful(false);
            return promotion;
        }
        promotion.setMessage("新增成功");
        promotion.setSuccessful(true);

        return promotion;
    }


    @Override
    public Promotion edit(Promotion promotion) {
        final Promotion oPromotion = Dao.selectById(promotion.getPromotionName());

        if (promotion.getPromotionName() != null) {
            oPromotion.setPromotionName(promotion.getPromotionName());
        }
        if (promotion.getPromotionType() != null) {
            oPromotion.setPromotionType(promotion.getPromotionType());
        }
        if (promotion.getPromotionMethod() != null) {
            oPromotion.setPromotionMethod(promotion.getPromotionMethod());
        }
        if (promotion.getAdminNo() != null) {
            oPromotion.setAdminNo(promotion.getAdminNo());
        }
        if (promotion.getCounterNo() != null) {
            oPromotion.setCounterNo(promotion.getCounterNo());
        }
        final int resultCount =Dao.update(oPromotion);
        oPromotion.setSuccessful(resultCount > 0);
        return oPromotion;

    }

    @Override
    public List<Promotion> findAll() {
        return Dao.selectAll();
    }

    @Override
    public List<Promotion> findbycounterNo(Integer counterNo){
        List<Promotion> promotions =Dao.selectbycountNo(counterNo);
        return  promotions;
    };


    @Override
    public boolean remove(String promotionName) {
        try {
            Dao.deleteByPK(promotionName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
