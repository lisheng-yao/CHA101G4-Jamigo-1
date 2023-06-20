package com.jamigo.shop.platform_order.service.impl;

import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.member.member_data.dto.MemberDataForCheckoutDTO;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import com.jamigo.shop.cart.entity.Cart;
import com.jamigo.shop.cart.repo.CartRepository;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.repo.PlatformOrderRepository;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import com.jamigo.shop.product_picture.repo.ProductPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlatformOrderServiceImpl implements PlatformOrderService {

    @Autowired
    private MemberDataDAO memberDataDAO;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductPictureRepository productPictureRepository;
    @Autowired
    private PlatformOrderRepository platformOrderRepository;

    @Override
    public MemberDataForCheckoutDTO getMemberData(Integer memberNo) {

        MemberData memberData = memberDataDAO.selectById(memberNo);  // 取得會員的全部資料

        MemberDataForCheckoutDTO memberDataForCheckoutDTO = new MemberDataForCheckoutDTO();

        // 將結帳會用到的資料封裝起來
        memberDataForCheckoutDTO.setMemberName(memberData.getMemberName());
        memberDataForCheckoutDTO.setMemberPhone(memberData.getMemberPhone());
        memberDataForCheckoutDTO.setMemberEmail(memberData.getMemberEmail());

        return memberDataForCheckoutDTO;
    }

    @Override
    public Map<String, List<CartForCheckoutDTO>> getCartInfo(Integer memberNo) {

        List<Cart> cartFullInfo = cartRepository.findByIdMemberNo(memberNo);  // 取得購物車的全部資料

        // 使用 Java 8 的 Stream API 對購物車資訊進行處理
        return cartFullInfo.stream()
                .map(cart -> new CartForCheckoutDTO(
                        cart.getProduct().getCounter().getCounterName(),
                        cart.getProduct().getProductNo(),
                        cart.getProduct().getProductName(),
                        cart.getProduct().getProductPrice(),
                        cart.getAmount()))  // 透過 map 操作，將每個 Cart 物件轉換為 CartForCheckoutDTO 物件
                                            // 在此過程中，我們建立新的 CartForCheckoutDTO 物件並將 Cart 物件中的各個欄位轉換並填入對應的 CartForCheckoutDTO 欄位
                .collect(Collectors.groupingBy(CartForCheckoutDTO::getCounterName)); // 使用 groupingBy 操作進行分組，將 CartForCheckoutDTO 物件根據其櫃位名稱分組
                                                                                     // 結果為一個 Map 物件，其鍵為櫃位名稱，值為具有相同櫃位名稱的 CartForCheckoutDTO 物件列表
    }

    @Override
    public byte[] getFirstProductPic(Integer productNo) {
        return productPictureRepository.findFirstByProductNo(productNo).getProductPic();
    }

    @Override
    public List<PlatformOrder> findAll() {
        return platformOrderRepository.findAll();
    }

    @Override
    public PlatformOrder findById(Integer platformOrderNo) {
        return platformOrderRepository.findById(platformOrderNo).orElse(null);
    }

    @Override
    public void createOrder(PlatformOrder platformOrder) {

        if (platformOrder.getPaymentStat() == null)
            platformOrder.setPaymentStat((byte) 0);

        if (platformOrder.getPlatformOrderStat() == null)
            platformOrder.setPlatformOrderStat((byte) 0);

        platformOrderRepository.save(platformOrder);
    }
}
