package com.jamigo.shop.platform_order.controller;

import com.jamigo.member.member_data.dto.MemberDataForCheckoutDTO;
import com.jamigo.shop.cart.dao.CartDAO;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
public class PlatformOrderController {

    @Autowired
    private PlatformOrderService platformOrderService;

    @GetMapping("/shop/checkout/memberData/{memberNo}")
    public MemberDataForCheckoutDTO fillInMemberData(@PathVariable("memberNo") Integer memberNo) {
        return platformOrderService.fillInMemberData(memberNo);
    }

    @GetMapping("/shop/checkout/cart/{memberNo}")
    public Map<String, List<CartForCheckoutDTO>> getCartInfo(@PathVariable("memberNo") Integer memberNo) {
        return platformOrderService.getCartInfo(memberNo);
    }

    @GetMapping("platform/platform_order")
    public List<PlatformOrder> findAll() {
        return platformOrderService.findAll();
    }
}
