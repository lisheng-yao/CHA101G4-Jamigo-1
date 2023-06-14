package com.jamigo.shop.platform_order.dao.impl;

import com.jamigo.shop.platform_order.dao.PlatformOrderDAO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PlatformOrderDAOImpl implements PlatformOrderDAO {

    @PersistenceContext
    private Session session;

    @Override
    public List<PlatformOrder> selectAll() {
        final String hql = "FROM PlatformOrder ORDER BY id";
        return session
                .createQuery(hql, PlatformOrder.class)
                .getResultList();
    }
}
