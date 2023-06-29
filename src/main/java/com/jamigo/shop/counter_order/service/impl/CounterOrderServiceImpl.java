package com.jamigo.shop.counter_order.service.impl;

import com.jamigo.shop.counter_order.dto.CounterOrderForTableDTO;
import com.jamigo.shop.counter_order.dto.ProductDetailForCounterOrderDTO;
import com.jamigo.shop.counter_order.entity.CounterOrder;
import com.jamigo.shop.counter_order.repo.CounterOrderRepository;
import com.jamigo.shop.counter_order.service.CounterOrderService;
import com.jamigo.shop.counter_order_detail.entity.CounterOrderDetail;
import com.jamigo.shop.counter_order_detail.repo.CounterOrderDetailRepository;
import com.jamigo.shop.platform_order.dto.ProductDetailForPlatformOrderDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.repo.PlatformOrderRepository;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounterOrderServiceImpl implements CounterOrderService {

    @Autowired
    private PlatformOrderRepository platformOrderRepository;

    @Autowired
    private CounterOrderRepository counterOrderRepository;

    @Autowired
    private CounterOrderDetailRepository counterOrderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

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

    @Override
    public List<ProductDetailForCounterOrderDTO> getCounterOrderDetailById(Integer counterOrderNo) {

        List<CounterOrderDetail> counterOrderDetailList = counterOrderDetailRepository.findAllByIdCounterOrderNo(counterOrderNo);

        List<ProductDetailForCounterOrderDTO> productDetailForCounterOrderDTOList = new ArrayList<>();

        for (var counterOrderDetail : counterOrderDetailList) {

            ProductDetailForCounterOrderDTO productDetailForCounterOrderDTO = new ProductDetailForCounterOrderDTO();

            Integer productNo = counterOrderDetail.getId().getProductNo();
            Product product = productRepository.findById(productNo).orElse(null);

            if (product != null) {
                productDetailForCounterOrderDTO.setProductNo(productNo);
                productDetailForCounterOrderDTO.setProductName(product.getProductName());
                productDetailForCounterOrderDTO.setProductPrice(product.getProductPrice());
                productDetailForCounterOrderDTO.setAmount(counterOrderDetail.getAmount());
                productDetailForCounterOrderDTO.setOrderDetailStat(counterOrderDetail.getOrderDetailStat());
                productDetailForCounterOrderDTO.setEvalContent(counterOrderDetail.getEvalContent());
                productDetailForCounterOrderDTO.setEvalScore(counterOrderDetail.getEvalScore());

                productDetailForCounterOrderDTOList.add(productDetailForCounterOrderDTO);
            }
        }

        return productDetailForCounterOrderDTOList;
    }
}
