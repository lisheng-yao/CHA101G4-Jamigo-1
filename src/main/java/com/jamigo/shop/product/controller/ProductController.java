package com.jamigo.shop.product.controller;

import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.shop.product.dto.AddProductDTO;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.entity.ProductCategory;
import com.jamigo.shop.product.entity.ProductPic;
import com.jamigo.shop.product.repo.ProductPicRepository;
import com.jamigo.shop.product.service.ProductCategoryService;
import com.jamigo.shop.product.service.ProductPicService;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final ProductPicService productPicService;

    @Autowired
    public ProductController(ProductService productService,
                             ProductCategoryService productCategoryService,
                             ProductPicService productPicService){
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.productPicService = productPicService;
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

    @GetMapping("/getOneProduct/{productNo}")
    public Product getProductWithPics(@PathVariable Integer productNo){
        Product product = productService.getProductByNo(productNo);
        System.out.println("productNo = " + productNo);
        System.out.println(product);
        return product;
    }

    @PostMapping(path = "/updateProduct/{productNo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateProductWithPics(@PathVariable Integer productNo,
                                         @RequestParam(value = "productCatNo") Integer productCatNo,
                                         @RequestParam(value = "productName") String productName,
                                         @RequestParam(value = "productPrice") Integer productPrice,
                                         @RequestParam(value = "productInfo") String productInfo,
                                         @RequestParam(value = "productStatus") Boolean productStatus,
                                         @RequestParam(value = "productPic1") MultipartFile pic1,
                                         @RequestParam(value = "productPic2") MultipartFile pic2,
                                         @RequestParam(value = "productPic3") MultipartFile pic3,
                                         @RequestParam(value = "productPic4") MultipartFile pic4){

        productService.updateProductWordsInfo(productNo, productCatNo, productName, productPrice, productInfo, productStatus);
        productPicService.updateProductPic(productNo, pic1, pic2, pic3, pic4);
    }

}
