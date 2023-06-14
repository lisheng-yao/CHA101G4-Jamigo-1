package com.jamigo.shop.product_picture.dao.impl;

import com.jamigo.shop.product_picture.dao.ProductPictureDAO;
import com.jamigo.shop.product_picture.entity.ProductPicture;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
public class ProductPictureDAOImpl implements ProductPictureDAO {

    @PersistenceContext
    private Session session;

    @Override
    public byte[] selectFirstByProductNo(Integer productNo) {
        try {
            String hql = "FROM ProductPicture WHERE productNo = :productNo";
            ProductPicture productPicture = (ProductPicture) session.createQuery(hql)
                    .setParameter("productNo", productNo)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .uniqueResult();

            if (productPicture != null) {
                return productPicture.getProductPic();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
