package com.jamigo.shop.platform_order.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class OrderService {

    public String ecpayCheckout() {

        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);

        AllInOne all = new AllInOne("");

        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(uuId);
        obj.setMerchantTradeDate("2017/01/01 08:05:23");
        obj.setTotalAmount("50");
        obj.setTradeDesc("test Description");
        obj.setItemName("Jamigo Mall 商品");
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
        obj.setClientBackURL("http://localhost:8080/Jamigo/shop/main_page/%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.html");
        String form = all.aioCheckOut(obj, null);

        return form;
    }
}