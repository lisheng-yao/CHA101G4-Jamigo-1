package com.jamigo.shop.cart.dao.impl;

import com.jamigo.shop.cart.dao.CartDAO;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CartDAOImpl implements CartDAO {
    @PersistenceContext
    private Session session;

    @Override
    public Map<String, List<CartForCheckoutDTO>> selectByMemberNo(Integer memberNo) {
        String hql = "select new com.jamigo.shop.cart.dto.CartForCheckoutDTO" +
                "(counter.counterName, product.productNo, product.productName, product.productPrice, cart.amount)" +
                "from Cart cart " +
                "join cart.product product " +
                "join product.counter counter " +
                "where cart.id.memberNo = :memberNo " +
                "order by product.counter.counterNo";

        List<CartForCheckoutDTO> cartList = session.createQuery(hql, CartForCheckoutDTO.class)
                .setParameter("memberNo", memberNo)
                .getResultList();

        return cartList.stream()
                .collect(Collectors.groupingBy(CartForCheckoutDTO::getCounterName));
    }
}
