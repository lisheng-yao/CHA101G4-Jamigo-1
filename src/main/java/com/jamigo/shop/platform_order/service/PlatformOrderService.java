package com.jamigo.shop.platform_order.service;

import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.platform_order.dto.CreatePlatformOrderDTO;
import com.jamigo.shop.platform_order.dto.EditPlatformOrderDTO;
import com.jamigo.shop.platform_order.dto.MemberDataForCheckoutDTO;
import com.jamigo.shop.platform_order.dto.CounterOrderForPlatformOrderDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;

import java.util.List;
import java.util.Map;

public interface PlatformOrderService {

    /**
     * 根據會員編號，取得該會員的部分資料，用於結帳
     * @param memberNo 會員編號
     * @return 帶有會員的部分資料 (姓名、手機、Email、住址、會員等級) 的 DTO
     */
    MemberDataForCheckoutDTO getMemberDataForCheckout(Integer memberNo);

    /**
     * 根據會員編號，取得該會員的購物車資訊，用於結帳
     * @param memberNo 會員編號
     * @return { "櫃位A名稱": ["商品a", "商品b"], "櫃位B名稱": ["商品c"] } 類型的資料
     */
    Map<String, List<CartDTO>> getCartInfoByMemberNo(Integer memberNo);

    List<PlatformOrder> getAllPlatformOrder();

    PlatformOrder getPlatformOrderById(Integer platformOrderNo);

    Map<String, CounterOrderForPlatformOrderDTO> getPlatformOrderDetailById(Integer platformOrderNo);

    String createPlatformOrder(CreatePlatformOrderDTO newPlatformOrder);

    void changePaidStat(Integer platformOrderNo, String formData);

    void sendEmail(PlatformOrder platformOrder) throws Exception;

    String getEmailContent(PlatformOrder platformOrder, Map<String, byte[]> images) throws Exception;

    List<PlatformOrder> getAllPlatformOrderByMemberNo(Integer memberNo);

    void editPlatformOrderStat(Integer platformOrderNo, EditPlatformOrderDTO editPlatformOrderDTO);
}
