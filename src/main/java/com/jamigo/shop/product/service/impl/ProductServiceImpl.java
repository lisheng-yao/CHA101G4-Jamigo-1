package com.jamigo.shop.product.service.impl;

import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.shop.product.dto.AddProductDTO;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.entity.ProductCategory;
import com.jamigo.shop.product.entity.ProductPic;
import com.jamigo.shop.product.repo.ProductCategoryRepository;
import com.jamigo.shop.product.repo.ProductPicRepository;
import com.jamigo.shop.product.repo.ProductRepository;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductPicRepository productPicRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductPicRepository productPicRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productPicRepository = productPicRepository;
    }

    @Override
    public List<Product> getProductsByCounterNo(Integer counterNo) {
        return productRepository.findByCounterNo(counterNo);
    }

    @Override
    public void addProduct(AddProductDTO addProductDTO) {
        Product product = new Product();
        //改成用Integer
//        Counter counter = new Counter();
//        counter.setCounterNo(addProductDTO.getCounterNo());
//        product.setCounter(counter);
        product.setCounterNo(addProductDTO.getCounterNo());
        product.setProductCategory(productCategoryRepository.getReferenceById(addProductDTO.getProductCategory()));
        product.setProductName(addProductDTO.getProductName());
        product.setProductPrice(addProductDTO.getProductPrice());
        product.setProductInfo(addProductDTO.getProductInfo());
        product.setProductStat(addProductDTO.getProductStat());

        productRepository.save(product);
    }

    @Override
    public Product getProductByNo(Integer productNo) {
        //用findById把資料一起拿回來
        return productRepository.findById(productNo).get();
    }

    @Override
    public Product updateProductWordsInfo(Integer productNo, Integer productCatNo, String productName, Integer productPrice, String productInfo, Boolean productStatus) {

        productRepository.updateProduct(productNo, productCatNo, productName, productPrice, productInfo, productStatus);

        return productRepository.findById(productNo).orElse(null);

    }
}
