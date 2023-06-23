package com.jamigo.shop.cart.service;

import com.jamigo.shop.cart.dto.CartDTO;

import java.util.List;

public interface CartService {

    public String addOneToCart(CartDTO cartDTO, Integer memberNo);

    public List<CartDTO> findAllCartItem(Integer memberNo);
}
