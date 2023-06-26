package com.jamigo.shop.counter_order.repo;

import com.jamigo.shop.counter_order.entity.CounterOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterOrderRepository extends JpaRepository<CounterOrder, Integer> {

    List<CounterOrder> findAllByPlatformOrderNo(Integer platformOrderNo);
}
