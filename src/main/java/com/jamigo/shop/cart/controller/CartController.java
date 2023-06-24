package com.jamigo.shop.cart.controller;

import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.cart.service.CartService;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.service.ProductPicService;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public String addOneToCart(@RequestBody CartDTO cartDTO, HttpServletRequest request){
//        MemberData member = (MemberData) request.getSession().getAttribute("memberNo");
//        if (member != null){
//            Integer memberNo = member.getMemberNo();
//        }else {
//            return "導回登入頁面";
//        }
        Integer memberNo = 3;

        Product product = productService.getProductByNo(cartDTO.getProductNo());
        cartDTO.setProductPrice(product.getProductPrice());
        cartDTO.setProductName(product.getProductName());

        //將前端送來的購物車項目和會員編號放入購物車
        return cartService.addOneToCart(cartDTO, memberNo);
    }

}
