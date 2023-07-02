package com.jamigo.shop.others.repo;

import com.jamigo.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductForMainPageRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product " +
            "ORDER BY RAND() LIMIT :randNum", nativeQuery = true)
    List<Product> getRandomProducts(Integer randNum);

    @Query(value = "select * from product " +
            "WHERE counterNo = :counterNo " +
            "ORDER BY RAND() LIMIT :randNum", nativeQuery = true)
    List<Product> getRandomCounterProducts(Integer counterNo, Integer randNum);
}
