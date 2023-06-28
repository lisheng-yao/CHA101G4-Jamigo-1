package com.jamigo.shop.counter_order.service.impl;

import com.jamigo.shop.counter_order.dto.CounterOrderForTableDTO;
import com.jamigo.shop.counter_order.entity.CounterOrder;
import com.jamigo.shop.counter_order.repo.CounterOrderRepository;
import com.jamigo.shop.counter_order.service.CounterOrderService;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.repo.PlatformOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounterOrderServiceImpl implements CounterOrderService {

    @Autowired
    private CounterOrderRepository counterOrderRepository;

    @Autowired
    private PlatformOrderRepository platformOrderRepository;

    @Override
    public List<CounterOrderForTableDTO> getCounterOrderByCounterNo(Integer counterNo) {

        List<CounterOrder> counterOrders =  counterOrderRepository.findAllByCounterNo(counterNo);
        List<CounterOrderForTableDTO> counterOrderForTableDTOList = new ArrayList<>();

        for (var counterOrder : counterOrders) {
            CounterOrderForTableDTO dto = new CounterOrderForTableDTO(
                    counterOrder.getCounterOrderNo(),
                    counterOrder.getPlatformOrderNo(),
                    counterOrder.getCounterNo(),
                    counterOrder.getTotalPaid(),
                    counterOrder.getActuallyPaid(),
                    counterOrder.getCounterOrderStat(),
                    counterOrder.getDisbursementStat()
            );

            PlatformOrder platformOrder = platformOrderRepository.findById(counterOrder.getPlatformOrderNo()).orElse(null);

            if (platformOrder != null)
                dto.setOrderTime(platformOrder.getOrderTime());

            counterOrderForTableDTOList.add(dto);
        }

        return counterOrderForTableDTOList;
    }
}
