package com.jamigo.shop.counter_order.service;

import com.jamigo.shop.counter_order.dto.CounterOrderForTableDTO;
import com.jamigo.shop.counter_order.dto.ProductDetailForCounterOrderDTO;
import com.jamigo.shop.platform_order.dto.ProductDetailForPlatformOrderDTO;

import java.util.List;

public interface CounterOrderService {

    List<CounterOrderForTableDTO> getCounterOrderByCounterNo(Integer counterNo);

    List<ProductDetailForCounterOrderDTO> getCounterOrderDetailById(Integer counterOrderNo);
}
