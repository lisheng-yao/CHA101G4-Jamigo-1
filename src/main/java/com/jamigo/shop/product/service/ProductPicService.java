package com.jamigo.shop.product.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductPicService {

    void updateProductPic(Integer productNo, MultipartFile pic1, MultipartFile pic2, MultipartFile pic3, MultipartFile pic4);
}
