package com.jamigo.shop.cart.service;

import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.cart.dto.CouponInfoDTO;

import java.util.List;

public interface CartService {

    String addOneToCart(CartDTO cartDTO, Integer memberNo);

    String changeOneInCart(CartDTO cartDTO, Integer memberNo);

    String deleteOneInCart(CartDTO cartDTO, Integer memberNo);

    List<CartDTO> findAllCartItem(Integer memberNo);

    //查詢會員擁有的所有折價券
    List<CouponInfoDTO> getCouponsByMemberNo(Integer memberNo);

    //查詢會員點數
    Integer getMemberPointsByNo(Integer memberNo);
}
