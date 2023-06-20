package com.jamigo.promotion.PromotionType.Dao.impl;

import com.jamigo.promotion.PromotionType.Dao.PromotionTypeDao;
import com.jamigo.promotion.PromotionType.Entity.Promotion;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class PromotionTypeDaoImpl implements PromotionTypeDao {

    @PersistenceContext
    private Session session;

    @Override
    @Transactional
    public List<Promotion> selectAll() {
        final String hql = "FROM Promotion order by promotionName";
        return session
                .createQuery(hql, Promotion.class)
                .getResultList();
    }

    @Override
    @Transactional
    public int update(Promotion promotion) {
        try {
            session.merge(promotion);
            return 1; // 更新成功，返回1
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 更新失败，返回0
        }
    }

    @Override
    @Transactional
    public int deleteByPK(String promotionName) {
        Promotion promotion = session.get(Promotion.class, promotionName);
        session.remove(promotion);
        return 1;
    }

    @Override
    @Transactional
    public int insert(Promotion promotion) {
        try {
            session.persist(promotion);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional
    public Promotion selectById(String promotionName) {
        Promotion resPromotion = session.get(Promotion.class, promotionName);
        return resPromotion;
    }
}
