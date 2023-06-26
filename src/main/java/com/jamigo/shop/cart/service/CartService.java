package com.jamigo.shop.cart.service;

import com.jamigo.shop.cart.dto.CartDTO;

import java.util.List;

public interface CartService {

    String addOneToCart(CartDTO cartDTO, Integer memberNo);

    String changeOneInCart(CartDTO cartDTO, Integer memberNo);

    String deleteOneInCart(CartDTO cartDTO, Integer memberNo);

    List<CartDTO> findAllCartItem(Integer memberNo);
}
