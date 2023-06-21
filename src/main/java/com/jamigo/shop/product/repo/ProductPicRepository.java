package com.jamigo.shop.product.repo;

import com.jamigo.shop.product.entity.ProductPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPicRepository extends JpaRepository<ProductPic, Integer> {

    //@Query("SELECT p FROM ProductPic p WHERE p.product.productNo = :productNo")
    @Query("SELECT p FROM ProductPic p WHERE p.productNo = :productNo")
    List<ProductPic> findByProductNo(@Param("productNo") Integer productNo);

}
