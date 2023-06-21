package com.jamigo.shop.product.service.impl;

import com.jamigo.shop.product.entity.ProductCategory;
import com.jamigo.shop.product.repo.ProductCategoryRepository;
import com.jamigo.shop.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository){
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryByNo(Integer productCatNo) {
        return productCategoryRepository.findById(productCatNo).get();
    }
}
