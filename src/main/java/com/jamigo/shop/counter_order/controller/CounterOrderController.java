package com.jamigo.shop.counter_order.controller;

import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.shop.counter_order.dto.CounterOrderForTableDTO;
import com.jamigo.shop.counter_order.dto.EditCounterOrderDTO;
import com.jamigo.shop.counter_order.dto.ProductDetailForCounterOrderDTO;
import com.jamigo.shop.counter_order.service.CounterOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CounterOrderController {

    @Autowired
    private CounterOrderService counterOrderService;

    @GetMapping("/shop/counter_order")
    public ResponseEntity<?> getAllCounterOrder(HttpSession session){

        Counter counter = (Counter) session.getAttribute("counter");

        List<CounterOrderForTableDTO> counterOrderForTableDTOList = counterOrderService.getCounterOrderByCounterNo(counter.getCounterNo());

        if (counterOrderForTableDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(counterOrderForTableDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/counter_order/{counterOrderNo}/detail")
    public ResponseEntity<?> getCounterOrderDetail(
            @PathVariable("counterOrderNo") Integer counterOrderNo) {

        List<ProductDetailForCounterOrderDTO> productDetailForCounterOrderDTOList = counterOrderService.getCounterOrderDetailById(counterOrderNo);

        if (productDetailForCounterOrderDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productDetailForCounterOrderDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/shop/counter_order/{counterOrderNo}/detail")
    public void getCounterOrderDetail(
            @PathVariable("counterOrderNo") Integer counterOrderNo,
            @RequestBody EditCounterOrderDTO editCounterOrderDTO) {

        counterOrderService.editCounterOrderDetailStat(counterOrderNo, editCounterOrderDTO);
    }
}
