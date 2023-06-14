package com.jamigo.shop.platform_order.dao;

import com.jamigo.shop.platform_order.entity.PlatformOrder;

import java.util.List;

public interface PlatformOrderDAO {
    List<PlatformOrder> selectAll();
}
