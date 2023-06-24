package com.jamigo.shop.cart.controller;

import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.cart.service.CartService;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.service.ProductPicService;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final ProductPicService productPicService;
    private final CartService cartService;

    @Autowired
    public CartController(ProductService productService, ProductPicService productPicService, CartService cartService){
        this.productService = productService;
        this.productPicService = productPicService;
        this.cartService = cartService;
    }

    @PostMapping("/addOneToCart")
    public String addOneToCart(@RequestBody Map<String, Object> cartData){

        //取得前端傳送的會員編號
        Integer memberNo = (Integer) cartData.get("memberNo");
        //取得前端傳來加入購物車項目
        Map<String, Object> cartItem = (Map<String, Object>) cartData.get("cartItem");
        Integer counterNo = (Integer) cartItem.get("counterNo");
        String counterName = (String) cartItem.get("counterName");
        Integer productNo = (Integer) cartItem.get("productNo");
        String productName = (String) cartItem.get("productName");
        Integer productPrice = (Integer) cartItem.get("productPrice");
        Integer quantity = (Integer) cartItem.get("quantity");

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCounterNo(counterNo);
        cartDTO.setCounterName(counterName);
        cartDTO.setProductNo(productNo);
        cartDTO.setProductName(productName);
        cartDTO.setProductPrice(productPrice);
        cartDTO.setQuantity(quantity);

        //將前端送來的購物車項目和會員編號放入購物車
        return cartService.addOneToCart(cartDTO, memberNo);
    }

}
