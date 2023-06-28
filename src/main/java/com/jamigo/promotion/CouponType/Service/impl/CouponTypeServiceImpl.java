package com.jamigo.promotion.CouponType.Service.impl;

import com.jamigo.promotion.CouponType.Dao.CouponTypeDao;
import com.jamigo.promotion.CouponType.Entity.CouponType;
import com.jamigo.promotion.CouponType.Service.CouponTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponTypeServiceImpl implements CouponTypeService {
    @Autowired
    private CouponTypeDao Dao;

    @Override
    public CouponType add(CouponType couponType) {
        if (couponType.getCouponTypeName() == null) {
            couponType.setMessage("種類名稱未輸入");
            couponType.setSuccessful(false);
            return couponType;
        }

        final int resultCount = Dao.insert(couponType);
        if (resultCount < 1) {
            couponType.setMessage("新增錯誤");
            couponType.setSuccessful(false);
            return couponType;
        }
        couponType.setMessage("新增成功");
        couponType.setSuccessful(true);

        return couponType;
    }


    @Override
    public CouponType edit(CouponType couponType) {
        final CouponType ocouponType = Dao.selectById(couponType.getCouponTypeNo());

        if (couponType.getCouponTypeName() != null) {
            ocouponType.setCouponTypeName(couponType.getCouponTypeName());
        }
        if (couponType.getAdminNo() != null) {
            ocouponType.setAdminNo(couponType.getAdminNo());
        }
        if (couponType.getCounterNo() != null) {
            ocouponType.setCounterNo(couponType.getCounterNo());
        }
        if (couponType.getCouponCreateDate() != null) {
            ocouponType.setCouponCreateDate(couponType.getCouponCreateDate());
        }
        if (couponType.getCouponConditions() != null) {
            ocouponType.setCouponConditions(couponType.getCouponConditions());
        }
        if (couponType.getCouponPrice() != null) {
            ocouponType.setCouponPrice(couponType.getCouponPrice());
        }
        if (couponType.getCouponExpireDate() != null) {
            ocouponType.setCouponExpireDate(couponType.getCouponExpireDate());
        }
        if (couponType.getCouponLowest() != null) {
            ocouponType.setCouponLowest(couponType.getCouponLowest());
        }


        final int resultCount =Dao.update(ocouponType);
        ocouponType.setSuccessful(resultCount > 0);
        return ocouponType;

    }

    @Override
    public CouponType findByCouponTypeNo(CouponType couponType) {
        final CouponType ocouponType = Dao.selectById(couponType.getCouponTypeNo());
        return ocouponType;
    }

    @Override
    public List<CouponType> findAll() {
        return Dao.selectAll();
    }


    @Override
    public boolean remove(Integer couponTypeNo) {
        try {
            Dao.deleteByPK(couponTypeNo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
