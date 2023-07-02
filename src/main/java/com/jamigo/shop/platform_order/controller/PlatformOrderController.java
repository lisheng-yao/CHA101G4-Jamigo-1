package com.jamigo.shop.platform_order.controller;

import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.platform_order.dto.MemberDataForCheckoutDTO;
import com.jamigo.shop.platform_order.dto.CounterOrderForPlatformOrderDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlatformOrderController {

    @Autowired
    private PlatformOrderService platformOrderService;


    // HTTP Method：GET
    // URL 階層關係：
    //     - "/shop/platform_order"：表示這個資源在 shop/platform_order 資料夾底下
    //     - "/memberData"：會員 (資源名稱)
    //     - "/{memberNo}"：會員編號 (資源 id)
    //
    // 完整說明：取得 shop/platform_order 底下，會員編號為 XXX 的會員資料 (僅有結帳所需的部分會員資料)
    //
    @GetMapping("/shop/platform_order/memberData/{memberNo}")
    public ResponseEntity<?> getMemberDataForCheckout(
            @PathVariable Integer memberNo) {

        MemberDataForCheckoutDTO memberDataForCheckoutDTO = platformOrderService.getMemberDataForCheckout(memberNo);

        if (memberDataForCheckoutDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(memberDataForCheckoutDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    // HTTP Method：GET
    // URL 階層關係：
    //     - "/shop/platform_order"：表示這個資源在 shop/platform_order 資料夾底下
    //     - "/cart"：購物車 (資源名稱)
    //     - "/{memberNo}"：會員編號 (資源 id)
    //
    // 完整說明：取得 shop/platform_order 底下，會員編號為 XXX 的購物車資料
    //
    // 目前僅為測試，日後可能會改為使用詒婷寫的 購物車 的 Controller
    @GetMapping("/shop/platform_order/cart/{memberNo}")
    public ResponseEntity<?> getCartInfo(
            @PathVariable("memberNo") Integer memberNo) {

        Map<String, List<CartDTO>> cartMap = platformOrderService.getCartInfoByMemberNo(memberNo);

        if (cartMap != null)
            return ResponseEntity.status(HttpStatus.OK).body(cartMap);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/shop/platform_order")
    public ResponseEntity<?> getAllPlatformOrder() {

        List<PlatformOrder> platformOrders = platformOrderService.getAllPlatformOrder();

        if (platformOrders != null)
            return ResponseEntity.status(HttpStatus.OK).body(platformOrders);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/shop/platform_order/{platformOrderNo}")
    public ResponseEntity<?> getPlatformOrder(
            @PathVariable("platformOrderNo") Integer platformOrderNo) {

        PlatformOrder platformOrder = platformOrderService.getPlatformOrderById(platformOrderNo);

        if (platformOrder != null)
            return ResponseEntity.status(HttpStatus.OK).body(platformOrder);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/shop/platform_order/{platformOrderNo}/detail")
    public ResponseEntity<?> getOrderDetail(
            @PathVariable("platformOrderNo") Integer platformOrderNo) {

        Map<String, CounterOrderForPlatformOrderDTO> orderDetailMap = platformOrderService.getPlatformOrderDetailById(platformOrderNo);

        if (orderDetailMap != null) {
            return ResponseEntity.status(HttpStatus.OK).body(orderDetailMap);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/shop/platform_order")
    public String createPlatformOrder(
            @RequestBody PlatformOrder newPlatformOrder) {

        return platformOrderService.createPlatformOrder(newPlatformOrder);
    }

    @PostMapping("/shop/platform_order/{platformOrderNo}/paidResult")
    public void checkPaidResult(
            @PathVariable("platformOrderNo") Integer platformOrderNo,
            @RequestBody String formData) {

        platformOrderService.changePaidStat(platformOrderNo, formData);
    }

    @GetMapping("/shop/platform_order/all/memberData/{memberNo}")
    public ResponseEntity<?> getAllPlatformOrderForMember(
            @PathVariable("memberNo") Integer memberNo
    ) {

        List<PlatformOrder> platformOrders = platformOrderService.getAllPlatformOrderByMemberNo(memberNo);

        if (platformOrders != null)
            return ResponseEntity.status(HttpStatus.OK).body(platformOrders);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}

