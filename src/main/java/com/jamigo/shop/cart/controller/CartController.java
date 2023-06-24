package com.jamigo.shop.cart.controller;

import com.google.gson.Gson;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.cart.service.CartService;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.service.ProductPicService;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        Gson gson = new Gson();
        String cartItemJson = gson.toJson(cartData.get("cartItem"));
        CartDTO cartDTO = gson.fromJson(cartItemJson, CartDTO.class);

        //將前端送來的購物車項目和會員編號放入購物車
        return cartService.addOneToCart(cartDTO, memberNo);
    }

    @GetMapping("/getCartList/{memberNo}")
    public List<CartDTO> getCartList(@PathVariable Integer memberNo){
        System.out.println(cartService.findAllCartItem(memberNo));
        return cartService.findAllCartItem(memberNo);
    }


}
