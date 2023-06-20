package com.jamigo.promotion.PromotionType.Dao.impl;

import com.jamigo.promotion.PromotionType.Dao.PromotionTypeDao;
import com.jamigo.promotion.PromotionType.Entity.Promotion;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class PromotionTypeDaoImpl implements PromotionTypeDao {

    @PersistenceContext
    private Session session;

    @Override
    public List<Promotion> selectAll() {
        final String hql = "FROM Promotion order by promotionName";
        return session
                .createQuery(hql, Promotion.class)
                .getResultList();
    }

    @Override
    public int update(Promotion promotion) {
        session.merge(promotion);
        return 1;
    }

    @Override
    public int deleteByPK(String promotionName) {
        Promotion promotion = session.get(Promotion.class, promotionName);
        session.remove(promotion);
        return 1;
    }

    @Override
    public int insert(Promotion promotion) {
        session.persist(promotion);
        return 1;
    }
}
