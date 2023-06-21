package com.jamigo.shop.product.repo;

import com.jamigo.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.counterNo = :counterNo")
    List<Product> findByCounterNo(@Param("counterNo") Integer counterNo);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET productCatNo = :productCatNo, productName = :productName, " +
            "productPrice = :productPrice, productInfo = :productInfo, productStat = :productStatus WHERE productNo = :productNo", nativeQuery = true)
    void updateProduct(Integer productNo, Integer productCatNo, String productName, Integer productPrice,
                       String productInfo, Boolean productStatus);

}
