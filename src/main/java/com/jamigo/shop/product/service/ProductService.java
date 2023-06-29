package com.jamigo.shop.product.service;

import com.jamigo.shop.product.dto.AddProductDTO;
import com.jamigo.shop.product.dto.ProductPageDTO;
import com.jamigo.shop.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;

public interface ProductService {

    List<Product> getProductsByCounterNo(Integer counterNo);

    void addProduct(AddProductDTO addProductDTO, Integer counterNo);

    Product getProductByNo(Integer productNo);

    Product updateProductWordsInfo(Integer productNo, Integer productCatNo, String productName, Integer productPrice, String productInfo, Boolean productStatus);

    ProductPageDTO getProductWithCounterName(Integer productNo);

}
