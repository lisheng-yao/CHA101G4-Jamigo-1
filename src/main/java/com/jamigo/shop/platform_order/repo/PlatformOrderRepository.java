package com.jamigo.shop.platform_order.repo;

import com.jamigo.shop.platform_order.dto.PlatformOrderDetailDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PlatformOrderRepository extends JpaRepository<PlatformOrder, Integer> {

    // 改為使用 Ting 的方法
//    @Query(value = "select new com.jamigo.shop.platform_order.dto.CartForCheckoutDTO" +
//            "(co.counterNo, co.counterName, p.productNo, p.productName, p.productPrice, c.amount) " +
//            "from Cart c " +
//            "join Product p on c.id.productNo = p.productNo " +
//            "join Counter co on p.counterNo = co.counterNo " +
//            "where c.id.memberNo = :memberNo")
//    List<CartForCheckoutDTO> getCartInfoByMemberNo(Integer memberNo);

    @Query(value = "select new com.jamigo.shop.platform_order.dto.PlatformOrderDetailDTO" +
            "(c.counterName, co.disbursementStat, cod.id.productNo, p.productName, p.productPrice, cod.amount, cod.orderDetailStat) " +
            "from CounterOrderDetail cod " +
            "join Product p on cod.id.productNo = p.productNo " +
            "join CounterOrder co on cod.id.counterOrderNo = co.counterOrderNo " +
            "join Counter c on co.counterNo = c.counterNo " +
            "join PlatformOrder po on co.platformOrderNo = po.platformOrderNo " +
            "where po.platformOrderNo = :platformOrderNo")
    List<PlatformOrderDetailDTO> getOrderDetailByPlatformOrderNo(Integer platformOrderNo);

    List<PlatformOrder> findAllByMemberNo(Integer memberNo);

    @Query("SELECT p FROM PlatformOrder p WHERE p.orderTime BETWEEN :start AND :end")
    List<PlatformOrder> findAllOrdersBetween(@Param("start") Timestamp start, @Param("end") Timestamp end);


    @Query("SELECT p FROM PlatformOrder p WHERE p.orderTime BETWEEN :start AND :end")
    List<PlatformOrder> findByOrderTimeBetween(Timestamp start, Timestamp end);

    Optional<PlatformOrder> findFirstByOrderByOrderTimeAsc();
}
