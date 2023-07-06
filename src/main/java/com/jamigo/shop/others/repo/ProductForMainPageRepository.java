package com.jamigo.shop.others.repo;

import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductForMainPageRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product " +
            "WHERE productStat = 1 " +
            "ORDER BY RAND() LIMIT :randNum", nativeQuery = true)
    List<Product> getRandomProducts(Integer randNum);

    @Query(value = "select * from product " +
            "WHERE counterNo = :counterNo and productStat = 1 " +
            "ORDER BY RAND() LIMIT :randNum", nativeQuery = true)
    List<Product> getRandomCounterProducts(Integer counterNo, Integer randNum);

    @Query(value = "select * from product " +
            "where productStat = 1 " +
            "ORDER BY productSaleNum DESC LIMIT 5", nativeQuery = true)
    List<Product> getBestSellingProducts();

    Page<Product> findByProductCategory(ProductCategory productCategory, Pageable pageable);

    @Query(value = "select count(*) from product p " +
            "where p.productCatNo = :productCatNo and productStat = 1", nativeQuery = true)
    Integer getProductAmountByCategory(Integer productCatNo);
}
