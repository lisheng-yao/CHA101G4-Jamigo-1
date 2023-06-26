package com.jamigo.shop.counter_order_detail.repo;

import com.jamigo.shop.counter_order_detail.entity.CounterOrderDetail;
import com.jamigo.shop.counter_order_detail.entity.CounterOrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterOrderDetailRepository extends JpaRepository<CounterOrderDetail, CounterOrderDetailId> {

    List<CounterOrderDetail> findAllByIdCounterOrderNo(Integer counterOrderNo);
}
