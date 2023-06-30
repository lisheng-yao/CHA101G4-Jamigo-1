package com.jamigo.promotion.PromotionCoupon.Service.impl;

import com.jamigo.promotion.PromotionCoupon.Dao.PromotionCouponDao;
import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import com.jamigo.promotion.PromotionCoupon.Service.PromotionCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionCouponServiceImpl implements PromotionCouponService {
    @Autowired
    private PromotionCouponDao Dao;

    @Override
    public PromotionCoupon add(PromotionCoupon promotionCoupon) {
        if (promotionCoupon.getPromotionCouponName() == null) {
            promotionCoupon.setMessage("種類名稱未輸入");
            promotionCoupon.setSuccessful(false);
            return promotionCoupon;
        }

        final PromotionCoupon result = Dao.save(promotionCoupon);
        if (result == null) {
            promotionCoupon.setMessage("新增錯誤");
            promotionCoupon.setSuccessful(false);
            return promotionCoupon;
        }
        promotionCoupon.setMessage("新增成功");
        promotionCoupon.setSuccessful(true);

        return promotionCoupon;
    }


    @Override
    public PromotionCoupon edit(PromotionCoupon promotionCoupone) {
        Optional<PromotionCoupon> opromotionCoupone = Dao.findById(promotionCoupone.getPromotionCouponNo());
        if (opromotionCoupone.isPresent()) {//確認opromotionCoupone是否為空
            PromotionCoupon oldpromotionCoupon = opromotionCoupone.get();//將它取出以更改值

            if (promotionCoupone.getPromotionCouponName() != null) {//若名稱不為空則取代舊值
                oldpromotionCoupon.setPromotionCouponName(promotionCoupone.getPromotionCouponName());
            }
            if (promotionCoupone.getPromotionName() != null) {
                oldpromotionCoupon.setPromotionName(promotionCoupone.getPromotionName());
            }
            if (promotionCoupone.getCouponTypeNo() != null) {
                oldpromotionCoupon.setCouponTypeNo(promotionCoupone.getCouponTypeNo());
            }
            if (promotionCoupone.getAmountOfCoupon() != null) {
                oldpromotionCoupon.setAmountOfCoupon(promotionCoupone.getAmountOfCoupon());
            }
            if (promotionCoupone.getGetCouponLimitLevel() != null) {
                oldpromotionCoupon.setGetCouponLimitLevel(promotionCoupone.getGetCouponLimitLevel());
            }
            if (promotionCoupone.getGetCouponLimitAmount() != null) {
                oldpromotionCoupon.setGetCouponLimitAmount(promotionCoupone.getGetCouponLimitAmount());
            }
            if (promotionCoupone.getPromotionEffectiveDate() != null) {
                oldpromotionCoupon.setPromotionEffectiveDate(promotionCoupone.getPromotionEffectiveDate());
            }
            if (promotionCoupone.getPromotionExpireDate() != null) {
                oldpromotionCoupon.setPromotionExpireDate(promotionCoupone.getPromotionExpireDate());
            }
            if (promotionCoupone.getGetAmount() != null) {
                oldpromotionCoupon.setGetAmount(promotionCoupone.getGetAmount());
            }
            if (promotionCoupone.getPromotionPic() != null) {
                oldpromotionCoupon.setPromotionPic(promotionCoupone.getPromotionPic());
            }

            final PromotionCoupon result = Dao.save(oldpromotionCoupon);
            oldpromotionCoupon.setSuccessful(result != null);
            oldpromotionCoupon.setMessage("修改成功");
            return oldpromotionCoupon;
        } else {
            return null;
        }
    }

    @Override
    public List<PromotionCoupon> findAll() {
        return Dao.findAll();
    }

    @Override
    public List<PromotionCoupon> findbcounterNo(Integer counterNo) {
        List<Integer> promotionCoupons = Dao.findCouponTypeNosByCounterNo(counterNo);
        List<PromotionCoupon> promotionCoupons2 = Dao.findByCouponTypeNoIn(promotionCoupons);
        return promotionCoupons2;
    }

    ;

    @Override
    public PromotionCoupon findByPK(Integer promotionCouponNo) {
        Optional<PromotionCoupon> promotionCoupon = Dao.findById(promotionCouponNo);
        PromotionCoupon promotionCoupona = null;
        if (promotionCoupon.isPresent()) {//確認opromotionCoupone是否為空
            promotionCoupona = promotionCoupon.get();
        }
        return promotionCoupona;
    }

    @Override
    public boolean remove(Integer promotionCouponNo) {
        try {
            Dao.deleteById(promotionCouponNo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
