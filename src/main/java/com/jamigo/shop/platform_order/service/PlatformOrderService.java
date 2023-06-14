package com.jamigo.shop.platform_order.service;

import com.jamigo.member.member_data.dto.MemberDataForCheckoutDTO;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;

import java.util.List;
import java.util.Map;

public interface PlatformOrderService {
    MemberDataForCheckoutDTO fillInMemberData(Integer memberNo);

    Map<String, List<CartForCheckoutDTO>> getCartInfo(Integer memberNo);

    List<PlatformOrder> findAll();
}
