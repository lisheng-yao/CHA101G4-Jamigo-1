package com.jamigo.shop.product_picture.repo;

import com.jamigo.shop.product_picture.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer>{

    /**
     * 根據商品編號，找到該商品所對應的第一張圖片資料
     * @param productNo 商品編號
     * @return ProductPicture 物件，為 product_picture 表格中的一個 data row
     */
    ProductPicture findFirstByProductNo(Integer productNo);
}
