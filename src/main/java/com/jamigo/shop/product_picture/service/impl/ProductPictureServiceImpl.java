package com.jamigo.shop.product_picture.service.impl;

import com.jamigo.shop.product_picture.dao.ProductPictureDAO;
import com.jamigo.shop.product_picture.service.ProductPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPictureServiceImpl implements ProductPictureService {

    @Autowired
    private ProductPictureDAO productPictureDAO;

    @Override
    public byte[] getFirstPicture(Integer productNo) {
        return productPictureDAO.selectFirstByProductNo(productNo);
    }
}
