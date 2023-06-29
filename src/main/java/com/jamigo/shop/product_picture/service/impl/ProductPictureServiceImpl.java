package com.jamigo.shop.product_picture.service.impl;

import com.jamigo.shop.product_picture.entity.ProductPicture;
import com.jamigo.shop.product_picture.repo.ProductPictureRepository;
import com.jamigo.shop.product_picture.service.ProductPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPictureServiceImpl implements ProductPictureService {

    @Autowired
    private ProductPictureRepository productPictureRepository;

    @Override
    public byte[] getFirstProductPicByProductNo(Integer productNo) {

        // 取得商品圖片表格中的一個 row data
        ProductPicture productPictureRow = productPictureRepository.findFirstByProductNo(productNo);

        // 以 byte[] 陣列的形式回傳圖片
        return productPictureRow.getProductPic();
    }
}
