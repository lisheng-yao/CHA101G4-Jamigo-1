package com.jamigo.shop.product.service.impl;

import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.shop.product.dto.AddProductDTO;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.entity.ProductCategory;
import com.jamigo.shop.product.repo.ProductCategoryRepository;
import com.jamigo.shop.product.repo.ProductRepository;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository){
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<Product> getProductsByCounterNo(Integer counterNo) {
        return productRepository.findByCounterNo(counterNo);
    }

    @Override
    public void addProduct(AddProductDTO addProductDTO) {
        Product product = new Product();
        Counter counter = new Counter();
        counter.setCounterNo(addProductDTO.getCounterNo());
        product.setCounter(counter);
        product.setProductCategory(productCategoryRepository.getReferenceById(addProductDTO.getProductCategory()));
        product.setProductName(addProductDTO.getProductName());
        product.setProductPrice(addProductDTO.getProductPrice());
        product.setProductInfo(addProductDTO.getProductInfo());
        product.setProductStat((byte) (addProductDTO.getProductStat() ? 1 : 0));

        productRepository.save(product);
    }
}
