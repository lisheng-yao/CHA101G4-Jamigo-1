package com.jamigo.promotion.PromotionPoint.Service.impl;

import com.jamigo.promotion.PromotionPoint.Dao.PromotionPointDao;
import com.jamigo.promotion.PromotionPoint.Entity.PromotionPoint;
import com.jamigo.promotion.PromotionPoint.Service.PromotionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionPointServiceImpl implements PromotionPointService {
    @Autowired
    private PromotionPointDao Dao;

    @Override
    public PromotionPoint add(PromotionPoint promotionPoint) {
        if (promotionPoint.getPromotionPointName() == null) {
            promotionPoint.setMessage("名稱未輸入");
            promotionPoint.setSuccessful(false);
            return promotionPoint;
        }
        if (promotionPoint.getGetPointMag() == null) {
            promotionPoint.setMessage("倍率未輸入");
            promotionPoint.setSuccessful(false);
            return promotionPoint;
        }
        if (promotionPoint.getPromotionName() == null) {
            promotionPoint.setMessage("活動類別未選擇");
            promotionPoint.setSuccessful(false);
            return promotionPoint;
        }
        if (promotionPoint.getPromotionEffectiveDate() == null) {
            promotionPoint.setMessage("日期未輸入");
            promotionPoint.setSuccessful(false);
            return promotionPoint;
        }
        if (promotionPoint.getPromotionExpireDate() == null) {
            promotionPoint.setMessage("日期未輸入");
            promotionPoint.setSuccessful(false);
            return promotionPoint;
        }

        final PromotionPoint result = Dao.save(promotionPoint);
        if (result == null) {
            promotionPoint.setMessage("新增錯誤");
            promotionPoint.setSuccessful(false);
            return promotionPoint;
        }
        promotionPoint.setMessage("新增成功");
        promotionPoint.setSuccessful(true);

        return promotionPoint;
    }


    @Override
    public PromotionPoint edit(PromotionPoint promotionPoint) {
        Optional<PromotionPoint> opromotionPoint = Dao.findById(promotionPoint.getPromotionPointNo());
        if (opromotionPoint.isPresent()) {//確認opromotionCoupone是否為空
            PromotionPoint oldpromotionPoint = opromotionPoint.get();//將它取出以更改值

            if (promotionPoint.getPromotionName() != null) {//若名稱不為空則取代舊值
                oldpromotionPoint.setPromotionPointName(promotionPoint.getPromotionPointName());
            }
            if (promotionPoint.getGetPointMag() != null) {
                oldpromotionPoint.setGetPointMag(promotionPoint.getGetPointMag());
            }
            if (promotionPoint.getGetPointConditions() != null) {
                oldpromotionPoint.setGetPointConditions(promotionPoint.getGetPointConditions());
            }
            if (promotionPoint.getPromotionName() != null) {
                oldpromotionPoint.setPromotionName(promotionPoint.getPromotionName());
            }
            if (promotionPoint.getPromotionEffectiveDate() != null) {
                oldpromotionPoint.setPromotionEffectiveDate(promotionPoint.getPromotionEffectiveDate());
            }
            if (promotionPoint.getPromotionExpireDate() != null) {
                oldpromotionPoint.setPromotionExpireDate(promotionPoint.getPromotionExpireDate());
            }
            if (promotionPoint.getPromotionPic() != null) {
                oldpromotionPoint.setPromotionPic(promotionPoint.getPromotionPic());
            }


        final PromotionPoint result = Dao.save(oldpromotionPoint);
        oldpromotionPoint.setSuccessful(result != null);
        oldpromotionPoint.setMessage("修改成功");
        return oldpromotionPoint;
        }else {
            return null;
        }
    }

    @Override
    public List<PromotionPoint> findAll() {
        return Dao.findAll();
    }


    @Override
    public boolean remove(Integer promotionPointNo) {
        try {
            Dao.deleteById(promotionPointNo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
