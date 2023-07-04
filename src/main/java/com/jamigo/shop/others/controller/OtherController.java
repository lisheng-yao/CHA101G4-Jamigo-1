package com.jamigo.shop.others.controller;

import com.jamigo.shop.others.dto.ProductForMainPageDTO;
import com.jamigo.shop.others.service.OtherService;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OtherController {

    @Autowired
    private OtherService otherService;

    @GetMapping("/shop/recommendation")
    public ResponseEntity<?> getRecommendation() {

        List<ProductForMainPageDTO> productForMainPageDTOList = otherService.getRecommendation();

        if (productForMainPageDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productForMainPageDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/recommendation/counter/{counterNo}")
    public ResponseEntity<?> getCounterRecommendation(@PathVariable Integer counterNo) {

        List<ProductForMainPageDTO> productForMainPageDTOList = otherService.getCounterRecommendation(counterNo);

        if (productForMainPageDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productForMainPageDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/best_selling")
    public ResponseEntity<?> getBestSelling() {

        List<ProductForMainPageDTO> productForMainPageDTOList = otherService.getBestSelling();

        if (productForMainPageDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productForMainPageDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/main_page/product_category")
    public ResponseEntity<?> getAllProductCategory() {

        List<ProductCategory> productCategoryList = otherService.getAllProductCategory();

        if (productCategoryList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productCategoryList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/product_category/{productCatNo}")
    public ResponseEntity<?> getProductCategoryById(
            @PathVariable Integer productCatNo) {

        ProductCategory productCategory = otherService.getProductCategoryById(productCatNo);

        if (productCategory != null)
            return ResponseEntity.status(HttpStatus.OK).body(productCategory);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/product_category/{productCatNo}/products")
    public ResponseEntity<?> getProductsByCategory(
            @PathVariable Integer productCatNo,
            @RequestParam Integer page,
            @RequestParam Integer orderBy) {

        List<ProductForMainPageDTO> productForMainPageDTOList = otherService.getProductsByCategory(productCatNo, page, orderBy);

        if (productForMainPageDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productForMainPageDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/product_category/{productCatNo}/product_amount")
    public ResponseEntity<?> getProductAmountByCategory(
            @PathVariable Integer productCatNo) {

        Integer product_amount = otherService.getProductAmountByCategory(productCatNo);

        if (product_amount != null)
            return ResponseEntity.status(HttpStatus.OK).body(product_amount);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/product_search")
    public ResponseEntity<?> getProductsByKeyword(@RequestParam String keyword, @RequestParam Integer orderBy) {
        List<ProductForMainPageDTO> productForMainPageDTOList = otherService.getProductsByKeyword(keyword, orderBy);

        if (productForMainPageDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productForMainPageDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
