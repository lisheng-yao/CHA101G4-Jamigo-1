package com.jamigo.shop.platform_order.controller;

import com.jamigo.member.member_data.dto.MemberDataForCheckoutDTO;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import com.jamigo.shop.platform_order.dto.CounterOrderForPlatformOrderDTO;
import com.jamigo.shop.platform_order.dto.PlatformOrderDetailDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import com.jamigo.shop.platform_order.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @GetMapping("/shop/platform_order/memberData/{memberNo}")
    public ResponseEntity<?> autoFillMemberData(
            @PathVariable Integer memberNo) {

        MemberDataForCheckoutDTO memberDataForCheckoutDTO = platformOrderService.getMemberData(memberNo);

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

        Map<String, List<CartForCheckoutDTO>> cartMap = platformOrderService.getCartItemByMemberNo(memberNo);

        if (cartMap != null)
            return ResponseEntity.status(HttpStatus.OK).body(cartMap);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // HTTP Method：GET
    @GetMapping("/shop/platform_order/product_picture/{productNo}")
    public ResponseEntity<?> getFirstProductPic(
            @PathVariable("productNo") Integer productNo) {

        // 取得商品編號為 productNo 的商品的首張圖片
        byte[] image = platformOrderService.getFirstProductPicByProductNo(productNo);

        // 建立 HttpHeaders 物件來設置 HTTP 回應的 headers
        HttpHeaders headers = new HttpHeaders();
        // 將 Content-Type header 設置為 IMAGE_GIF，表示我們將回傳圖片
        headers.setContentType(MediaType.IMAGE_GIF);

        if (image != null && image.length > 0) {
            // 如果圖片存在 (也就是 byte 數組不為 null 且長度大於 0)，則回傳 HTTP OK 狀態碼 (200)，以及圖片
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } else {
            // 如果圖片不存在，則回傳 HTTP NO CONTENT 狀態碼 (204)
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
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

    @GetMapping("/shop/platform_order/detail/{platformOrderNo}")
    public ResponseEntity<?> getOrderDetail(
            @PathVariable("platformOrderNo") Integer platformOrderNo) {

        List<PlatformOrderDetailDTO> orderDetail = platformOrderService.getOrderDetailById(platformOrderNo);

        if (orderDetail != null) {
            Map<String, CounterOrderForPlatformOrderDTO> orderDetailMap = platformOrderService.convertToCounterOrderMap(orderDetail);
            return ResponseEntity.status(HttpStatus.OK).body(orderDetailMap);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/shop/platform_order")
    public void createPlatformOrder(
            @RequestBody PlatformOrder newPlatformOrder) {

        platformOrderService.createOrder(newPlatformOrder);
    }

    @PostMapping("/test/register")
    public String register(@RequestBody UserInfo userInfo) throws Exception {
        platformOrderService.sendEmail();
        return "Email Sent..!";
    }
}
