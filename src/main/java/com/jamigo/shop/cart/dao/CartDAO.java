package com.jamigo.shop.cart.dao;

import com.jamigo.shop.cart.dto.CartForCheckoutDTO;

import java.util.List;
import java.util.Map;

public interface CartDAO {
    Map<String, List<CartForCheckoutDTO>> selectByMemberNo(Integer memberNo);
}
