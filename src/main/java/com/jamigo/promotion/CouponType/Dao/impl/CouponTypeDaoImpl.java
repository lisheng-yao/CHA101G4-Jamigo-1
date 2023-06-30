package com.jamigo.promotion.CouponType.Dao.impl;

import com.jamigo.promotion.CouponType.Dao.CouponTypeDao;
import com.jamigo.promotion.CouponType.Entity.CouponType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class CouponTypeDaoImpl implements CouponTypeDao {

    @PersistenceContext
    private Session session;

    @Override
    @Transactional
    public List<CouponType> selectAll() {
        final String hql = "FROM CouponType  order by couponTypeNo ";
        return session
                .createQuery(hql, CouponType.class)
                .getResultList();
    }

    @Override
    @Transactional
    public List<CouponType> selectBycounterNo(Integer counterNo) {
        final String hql = "FROM CouponType WHERE counterNo = ?1";
        return session
                .createQuery(hql, CouponType.class)
                .setParameter(1, counterNo)
                .getResultList();
    }

    @Override
    @Transactional
    public int update(CouponType couponType) {
        try {
            session.merge(couponType);
            return 1; // 更新成功，返回1
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 更新失败，返回0
        }
    }

    @Override
    @Transactional
    public int deleteByPK(Integer couponTypeNo) {
        CouponType couponType = session.get(CouponType.class, couponTypeNo);
        session.remove(couponType);
        return 1;
    }

    @Override
    @Transactional
    public int insert(CouponType couponType) {
        try {
            session.persist(couponType);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional
    public CouponType selectById(Integer couponTypeNo) {
        CouponType couponType = session.get(CouponType.class, couponTypeNo);
        return couponType;
    }
}
