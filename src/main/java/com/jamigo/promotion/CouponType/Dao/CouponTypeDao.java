package com.jamigo.promotion.CouponType.Dao;

import com.jamigo.promotion.CouponType.Entity.CouponType;

import java.util.List;

public interface CouponTypeDao {
    List<CouponType> selectAll();
    int update(CouponType couponType);
    int deleteByPK(Integer couponTypeNo);
    int insert(CouponType couponType);

    CouponType selectById(Integer couponTypeNo);
}
