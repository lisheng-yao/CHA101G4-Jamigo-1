package com.jamigo.shop.product.controller;

import com.jamigo.shop.product.dto.AddProductDTO;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.entity.ProductCategory;
import com.jamigo.shop.product.service.ProductCategoryService;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductController(ProductService productService, ProductCategoryService productCategoryService){
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/listAllCounterProducts/{counterNo}")
    public List<Product> getProductsByCounterNo(@PathVariable Integer counterNo){
        System.out.println(productService.getProductsByCounterNo(counterNo));
        return productService.getProductsByCounterNo(counterNo);
    }

    @GetMapping("/getAllCategories")
    public List<ProductCategory> getProductCategoryOptions(){
        System.out.println(productCategoryService.findAll());
        return productCategoryService.findAll();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddProductDTO addProductDTO){
        try {
            productService.addProduct(addProductDTO);
            return ResponseEntity.ok("Product added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product");
        }
    }
}
