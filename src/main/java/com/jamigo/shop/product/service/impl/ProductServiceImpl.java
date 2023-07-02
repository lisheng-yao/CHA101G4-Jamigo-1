package com.jamigo.shop.product.service.impl;

import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.shop.product.dto.AddProductDTO;
import com.jamigo.shop.product.dto.ProductPageDTO;
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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductPicRepository productPicRepository;
    private final EntityManager entityManager;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductPicRepository productPicRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productPicRepository = productPicRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> getProductsByCounterNo(Integer counterNo) {
        return productRepository.findByCounterNo(counterNo);
    }

    @Override
    public void addProduct(AddProductDTO addProductDTO, Integer counterNo) {
        Product product = new Product();
        //改成用Integer
//        Counter counter = new Counter();
//        counter.setCounterNo(addProductDTO.getCounterNo());
//        product.setCounter(counter);
//        product.setCounterNo(addProductDTO.getCounterNo());
        product.setCounterNo(counterNo);
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

    public ProductPageDTO getProductWithCounterName(Integer productNo){
        Product product = productRepository.findById(productNo).get();

        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT counter.counterName " +
                        "FROM product " +
                        "JOIN counter ON product.counterNo = counter.counterNo " +
                        "WHERE product.productNo = :productNo"
        );
        nativeQuery.setParameter("productNo", productNo);

        String counterName = (String) nativeQuery.getSingleResult();

        ProductPageDTO productPageDTO = new ProductPageDTO();
        productPageDTO.setProductNo(product.getProductNo());
        productPageDTO.setProductCategory(product.getProductCategory());
        productPageDTO.setCounterNo(product.getCounterNo());
        productPageDTO.setProductName(product.getProductName());
        productPageDTO.setProductPrice(product.getProductPrice());
        productPageDTO.setProductInfo(product.getProductInfo());
        productPageDTO.setProductStat(product.getProductStat());
        productPageDTO.setProductSaleNum(product.getProductSaleNum());
        productPageDTO.setReportNumber(product.getReportNumber());
        productPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
        productPageDTO.setEvalTotalScore(product.getEvalTotalScore());
        productPageDTO.setProductPics(product.getProductPics());
        productPageDTO.setCounterName(counterName);

        return productPageDTO;
    }


}
