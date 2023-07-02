package com.jamigo.shop.platform_order.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.jamigo.shop.platform_order.entity.PlatformOrder;
import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class OrderService {

    public String ecpayCheckout(String form_data) {

        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formatDateTime = now.format(formatter);

        AllInOne all = new AllInOne("");

        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(uuId);
        obj.setMerchantTradeDate(formatDateTime);
//        obj.setTotalAmount(String.valueOf(newplatformOrder.getActuallyPaid()));

        obj.setTradeDesc("test Description");
        obj.setItemName("Jamigo Mall 商品");
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
        obj.setClientBackURL("http://localhost:8080/Jamigo/shop/main_page/%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.html");
        String form = all.aioCheckOut(obj, null);

        return form;
    }
}