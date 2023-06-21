package com.jamigo.shop.platform_order.service;

import com.jamigo.member.member_data.dto.MemberDataForCheckoutDTO;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;

import java.util.List;
import java.util.Map;

public interface PlatformOrderService {
    /**
     * 根據會員編號，取得該會員的部分資料，用於結帳
     * @param memberNo 會員編號
     * @return 帶有會員的部分資料 (姓名、手機、Email、住址) 的 DTO
     */
    MemberDataForCheckoutDTO getMemberData(Integer memberNo);

    /**
     * 根據會員編號，取得該會員的購物車資訊，用於結帳
     * @param memberNo 會員編號
     * @return { "櫃位A名稱": ["商品a", "商品b"], "櫃位B名稱": ["商品c"] } 類型的資料
     */
    Map<String, List<CartForCheckoutDTO>> getCartInfo(Integer memberNo);

    /**
     * 根據商品編號，取得該商品的第一張圖片
     * @param productNo 商品編號
     * @return 以 byte 陣列儲存的圖片
     */
    byte[] getFirstProductPic(Integer productNo);

    List<PlatformOrder> getAllPlatformOrder();

    PlatformOrder getPlatformOrderById(Integer platformOrderNo);

    void createOrder(PlatformOrder platformOrder);
}
