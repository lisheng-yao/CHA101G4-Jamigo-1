package com.jamigo.shop.product_picture.controller;

import com.jamigo.shop.product_picture.service.ProductPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/product_picture")
public class ProductPictureController {

    @Autowired
    private ProductPictureService productPictureService;

    @GetMapping("{productNo}")
    public ResponseEntity<byte[]> getFirstPicture(@PathVariable("productNo") Integer productNo) {
        byte[] image = productPictureService.getFirstPicture(productNo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);  // 假設圖片為 PNG 格式

        if (image != null && image.length > 0) {
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
    }
}
