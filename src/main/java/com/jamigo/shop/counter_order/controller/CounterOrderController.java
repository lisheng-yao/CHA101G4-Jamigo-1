package com.jamigo.shop.counter_order.controller;

import com.jamigo.counter.counter.service.CounterService;
import com.jamigo.shop.counter_order.dto.CounterOrderForTableDTO;
import com.jamigo.shop.counter_order.entity.CounterOrder;
import com.jamigo.shop.counter_order.service.CounterOrderService;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CounterOrderController {

    @Autowired
    private CounterOrderService counterOrderService;

    @GetMapping("/shop/counter/{counterNo}/counter_order")
    public ResponseEntity<?> getAllCounterOrder(
            @PathVariable("counterNo") Integer counterNo) {

        List<CounterOrderForTableDTO> counterOrderForTableDTOList = counterOrderService.getCounterOrderByCounterNo(counterNo);

        if (counterOrderForTableDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(counterOrderForTableDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
