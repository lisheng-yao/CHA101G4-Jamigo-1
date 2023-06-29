package com.jamigo.shop.product_picture.controller;

import com.jamigo.shop.product_picture.service.ProductPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductPictureController {

    @Autowired
    private ProductPictureService service;

    // HTTP Method：GET
    @GetMapping("/shop/product_picture/product/{productNo}")
    public ResponseEntity<?> getFirstProductPic(
            @PathVariable("productNo") Integer productNo) {

        // 取得商品編號為 productNo 的商品的首張圖片
        byte[] image = service.getFirstProductPicByProductNo(productNo);

        // 建立 HttpHeaders 物件來設置 HTTP 回應的 headers
        HttpHeaders headers = new HttpHeaders();
        // 將 Content-Type header 設置為 IMAGE_GIF，表示我們將回傳圖片
        headers.setContentType(MediaType.IMAGE_GIF);

        if (image != null && image.length > 0) {
            // 如果圖片存在 (也就是 byte 數組不為 null 且長度大於 0)，則回傳 HTTP OK 狀態碼 (200)，以及圖片
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } else {
            // 如果圖片不存在，則回傳 HTTP NO CONTENT 狀態碼 (204)
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
    }
}
