package com.jamigo.shop.counter_order.service;

import com.jamigo.shop.counter_order.dto.CounterOrderForTableDTO;

import java.util.List;

public interface CounterOrderService {

    List<CounterOrderForTableDTO> getCounterOrderByCounterNo(Integer counterNo);
}
