package com.jamigo.shop.platform_order.repo;

import com.jamigo.shop.platform_order.entity.PlatformOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformOrderRepository extends JpaRepository<PlatformOrder, Integer> {
}
