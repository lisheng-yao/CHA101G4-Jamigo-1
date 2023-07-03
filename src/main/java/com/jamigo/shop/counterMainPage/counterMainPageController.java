package com.jamigo.shop.counterMainPage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.service.ProductService;

@RequestMapping("/products")
@RestController
public class counterMainPageController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/listAllCounterProducts/{counterNo}")
    public List<Product> getProductsByCounterNo(@PathVariable Integer counterNo){
		
        return productService.getProductsByCounterNo(counterNo);
        
    }
	
}
