package com.jamigo.shop.platform_order.controller;

import com.jamigo.member.member_data.dto.MemberDataDTO;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/checkout")
public class PlatformOrderController {

    @Autowired
    private PlatformOrderService platformOrderService;

    @GetMapping("memberData")
    public MemberDataDTO firstAutoFill() {
        return platformOrderService.getMemberData();
    }
}
