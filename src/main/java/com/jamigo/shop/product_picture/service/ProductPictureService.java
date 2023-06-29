package com.jamigo.shop.product_picture.service;

public interface ProductPictureService {

    /**
     * 根據商品編號，取得該商品的第一張圖片
     * @param productNo 商品編號
     * @return 以 byte 陣列儲存的圖片
     */
    byte[] getFirstProductPicByProductNo(Integer productNo);
}
