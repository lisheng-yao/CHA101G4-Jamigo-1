package com.jamigo.shop.main;

import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("成功啟動!");

//        List<Product> productList = productRepository.findAll();
//        for(Product p : productList){
//            System.out.println(p);
//        }
    }
}
