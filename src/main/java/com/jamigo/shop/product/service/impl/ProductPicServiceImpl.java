package com.jamigo.shop.product.service.impl;

import com.jamigo.shop.product.entity.ProductPic;
import com.jamigo.shop.product.repo.ProductPicRepository;
import com.jamigo.shop.product.service.ProductPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductPicServiceImpl implements ProductPicService {

    private final ProductPicRepository productPicRepository;

    @Autowired
    public ProductPicServiceImpl(ProductPicRepository productPicRepository){
        this.productPicRepository = productPicRepository;
    }

    @Override
    public void updateProductPic(Integer productNo, MultipartFile pic1, MultipartFile pic2, MultipartFile pic3, MultipartFile pic4) {

        productPicRepository.deleteByProductNo(productNo);

        MultipartFile[] files = {pic1, pic2, pic3, pic4};
//        List<byte[]> productPics = new ArrayList<>();
        for(int i = 0; i < files.length; i++){
            MultipartFile file = files[i];
            try {
                byte[] picData = file.getBytes();
                productPicRepository.insertProductPics(productNo, picData);
//                productPics.add(picData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
