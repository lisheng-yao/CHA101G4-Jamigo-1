package com.jamigo.shop.platform_order.controller;

import com.jamigo.shop.platform_order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/shop/platform_order/ecpayCheckout")
    public String ecpayCheckout(String form_data) {
        String aioCheckOutALLForm = orderService.ecpayCheckout(form_data);

        return aioCheckOutALLForm;
    }
}