package com.jamigo.shop.product.repo;

import com.jamigo.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.counter.counterNo = :counterNo")
    List<Product> findByCounterNo(@Param("counterNo") Integer counterNo);
}
