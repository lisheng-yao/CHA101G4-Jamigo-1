package com.jamigo.shop.product_picture.dao;

public interface ProductPictureDAO {
    byte[] selectFirstByProductNo(Integer productNo);
}
