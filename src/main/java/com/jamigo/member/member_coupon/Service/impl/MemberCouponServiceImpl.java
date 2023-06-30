package com.jamigo.member.member_coupon.Service.impl;

import com.jamigo.member.member_coupon.Service.MemberCouponService;
import com.jamigo.member.member_coupon.dao.MemberCouponDao;
import com.jamigo.member.member_coupon.entity.MemberCoupon;
import com.jamigo.member.member_coupon.entity.MemberCouponId;
import com.jamigo.promotion.PromotionCoupon.Dao.PromotionCouponDao;
import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import com.jamigo.promotion.PromotionCoupon.Service.PromotionCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberCouponServiceImpl implements MemberCouponService {
    @Autowired
    private MemberCouponDao Dao;

    @Override
    public Integer findbycouponTypeNo(Integer couponTypeNo) {
        List<MemberCoupon> list = Dao.findByCouponTypeNo(couponTypeNo);
        Integer biggest=0;
        for (MemberCoupon x : list) {
            Integer memberCouponNo= x.getMemberCouponId().getMemberCouponNo();
            if(biggest<memberCouponNo){
                biggest= memberCouponNo;
            }
        }
        biggest +=1;
       return biggest;
    }

    ;

    @Override
    public MemberCoupon add(MemberCoupon memberCoupon) {
//        ====判斷有沒有領過====
//        把傳進來的coupontypeno取出
        Integer TypeNo = memberCoupon.getMemberCouponId().getCouponTypeNo();
//        取出該會員所有折價券
        List<MemberCoupon> omemberCoupon = Dao.findByMemberNo(memberCoupon.getMemberNo());
//        一個一個比對
        for (MemberCoupon omembercoupon1 : omemberCoupon) {
            Integer oTypeNo = omembercoupon1.getMemberCouponId().getCouponTypeNo();
            if (oTypeNo == TypeNo) {
                memberCoupon.setMessage("重複領取");
                memberCoupon.setSuccessful(false);
                return memberCoupon;
            }
        }


        final MemberCoupon result = Dao.save(memberCoupon);
        if (result == null) {
            result.setMessage("領取失敗");
            result.setSuccessful(false);
            return result;
        }
        result.setMessage("領取成功");
        result.setSuccessful(true);

        return result;
    }


    @Override
    public MemberCoupon edit(MemberCoupon memberCoupon) {
        Optional<MemberCoupon> omemberCoupon1 = Dao.findById(memberCoupon.getMemberCouponId());
        if (omemberCoupon1.isPresent()) {//確認opromotionCoupone是否為空
            MemberCoupon omemberCoupon = omemberCoupon1.get();//將它取出以更改值

            if (memberCoupon.getCouponUsedStat() != null) {//若名稱不為空則取代舊值
                omemberCoupon.setCouponUsedStat(memberCoupon.getCouponUsedStat());
            }
            if (memberCoupon.getCouponUsedTime() != null) {
                omemberCoupon.setCouponUsedTime(memberCoupon.getCouponUsedTime());
            }
            if (memberCoupon.getOrderDetailCouponNo() != null) {
                omemberCoupon.setOrderDetailCouponNo(memberCoupon.getOrderDetailCouponNo());
            }

            final MemberCoupon result = Dao.save(omemberCoupon);
            omemberCoupon.setSuccessful(result != null);
            omemberCoupon.setMessage("修改成功");
            return omemberCoupon;
        } else {
            memberCoupon.setMessage("修改失敗");
            memberCoupon.setSuccessful(false);
            return memberCoupon;
        }
    }

    @Override
    public List<MemberCoupon> findAll() {
        return Dao.findAll();
    }


    @Override
    public List<MemberCoupon> selectByMember(MemberCoupon memberCoupon) {
        List<MemberCoupon> omemberCoupon = Dao.findByMemberNo(memberCoupon.getMemberNo());
        return omemberCoupon;
    }
}
