package com.jamigo.shop.product.service;

import com.jamigo.shop.product.dto.AddProductDTO;
import com.jamigo.shop.product.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductsByCounterNo(Integer counterNo);

    void addProduct(AddProductDTO addProductDTO);
}
