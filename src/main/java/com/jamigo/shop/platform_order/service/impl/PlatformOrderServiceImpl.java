package com.jamigo.shop.platform_order.service.impl;

import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.member.member_data.dto.MemberDataForCheckoutDTO;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import com.jamigo.shop.counter_order.entity.CounterOrder;
import com.jamigo.shop.counter_order.repo.CounterOrderRepository;
import com.jamigo.shop.counter_order_detail.entity.CounterOrderDetail;
import com.jamigo.shop.counter_order_detail.entity.CounterOrderDetailId;
import com.jamigo.shop.counter_order_detail.repo.CounterOrderDetailRepository;
import com.jamigo.shop.platform_order.dto.CounterOrderForPlatformOrderDTO;
import com.jamigo.shop.platform_order.dto.PlatformOrderDetailDTO;
import com.jamigo.shop.platform_order.dto.ProductForPlatformOrderDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.repo.PlatformOrderRepository;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import com.jamigo.shop.product_picture.entity.ProductPicture;
import com.jamigo.shop.product_picture.repo.ProductPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlatformOrderServiceImpl implements PlatformOrderService {

    @Autowired
    private MemberDataDAO memberDataDAO;
    @Autowired
    private ProductPictureRepository productPictureRepository;
    @Autowired
    private PlatformOrderRepository platformOrderRepository;
    @Autowired
    private CounterOrderRepository counterOrderRepository;
    @Autowired
    private CounterOrderDetailRepository counterOrderDetailRepository;

    @Override
    public MemberDataForCheckoutDTO getMemberData(Integer memberNo) {

        MemberData memberData = memberDataDAO.selectById(memberNo);  // 取得會員的全部資料

        if (memberData != null) {
            // 將結帳會用到的資料封裝起來
            MemberDataForCheckoutDTO memberDataForCheckoutDTO = new MemberDataForCheckoutDTO();

            memberDataForCheckoutDTO.setMemberName(memberData.getMemberName());
            memberDataForCheckoutDTO.setMemberPhone(memberData.getMemberPhone());
            memberDataForCheckoutDTO.setMemberEmail(memberData.getMemberEmail());
            memberDataForCheckoutDTO.setMemberAddress(memberData.getMemberAddress());

            return memberDataForCheckoutDTO;
        }
        else
            return null;
    }

    @Override
    public Map<String, List<CartForCheckoutDTO>> getCartItemByMemberNo(Integer memberNo) {

        List<CartForCheckoutDTO> cartList = platformOrderRepository.getCartItemByMemberNo(memberNo);

        return cartList.stream()
                .collect(Collectors.groupingBy(CartForCheckoutDTO::getCounterName));
    }

    @Override
    public byte[] getFirstProductPicByProductNo(Integer productNo) {

        // 取得商品圖片表格中的一個 row data
        ProductPicture productPictureRow = productPictureRepository.findFirstByProductNo(productNo);

        // 以 byte[] 陣列的形式回傳圖片
        return productPictureRow.getProductPic();
    }

    @Override
    public List<PlatformOrder> getAllPlatformOrder() {
        return platformOrderRepository.findAll();
    }

    @Override
    public PlatformOrder getPlatformOrderById(Integer platformOrderNo) {
        return platformOrderRepository.findById(platformOrderNo).orElse(null);
    }

    @Override
    public List<PlatformOrderDetailDTO> getOrderDetailById(Integer platformOrderNo) {

        return platformOrderRepository.getOrderDetailByPlatformOrderNo(platformOrderNo);
    }

    @Override
    public Map<String, CounterOrderForPlatformOrderDTO> convertToCounterOrderMap(List<PlatformOrderDetailDTO> orderDetails) {
        Map<String, CounterOrderForPlatformOrderDTO> map = new HashMap<>();

        for (PlatformOrderDetailDTO detail : orderDetails) {
            String counterName = detail.getCounterName();
            CounterOrderForPlatformOrderDTO counterOrder = map.get(counterName);

            if (counterOrder == null) {
                counterOrder = new CounterOrderForPlatformOrderDTO();
                counterOrder.setDisbursementStat(detail.getDisbursementStat());
                map.put(counterName, counterOrder);
            }

            ProductForPlatformOrderDTO productDetail = new ProductForPlatformOrderDTO();
            productDetail.setProductNo(detail.getProductNo());
            productDetail.setProductName(detail.getProductName());
            productDetail.setProductPrice(detail.getProductPrice());
            productDetail.setAmount(detail.getAmount());
            productDetail.setOrderDetailStat(detail.getOrderDetailStat());

            counterOrder.getProduct().add(productDetail);
        }

        return map;
    }

    @Override
    public void createOrder(PlatformOrder newPlatformOrder) {

        List<CartForCheckoutDTO> cartList = platformOrderRepository.getCartItemByMemberNo(newPlatformOrder.getMemberNo());

        int totalPaid = 0;

        for (var cartItem : cartList) {
            totalPaid += cartItem.getProductPrice() * cartItem.getAmount();
        }

        int actuallyPaid = totalPaid - newPlatformOrder.getTotalCoupon() - newPlatformOrder.getTotalPoints();
        int rewardPoints = Math.round(actuallyPaid / 10.0f);

        newPlatformOrder.setTotalPaid(totalPaid);
        newPlatformOrder.setActuallyPaid(actuallyPaid);
        newPlatformOrder.setRewardPoints(rewardPoints);

        switch (newPlatformOrder.getPaymentMethod()) {

            case 1:
            case 2:
                newPlatformOrder.setPlatformOrderStat((byte) 10);
                newPlatformOrder.setPaymentStat((byte) 0);
                break;

            case 3:
                newPlatformOrder.setPlatformOrderStat((byte) 20);
                newPlatformOrder.setPaymentStat((byte) 0);
        }

        PlatformOrder savedPlatformOrder = platformOrderRepository.save(newPlatformOrder);
        Integer platformOrderNo = savedPlatformOrder.getPlatformOrderNo();

        // 拆單
        Map<Integer, List<CartForCheckoutDTO>> cartMap = cartList.stream()
                .collect(Collectors.groupingBy(CartForCheckoutDTO::getCounterNo));

        for(var entry : cartMap.entrySet()) {
            Integer counterNo = entry.getKey();
            List<CartForCheckoutDTO> productList = entry.getValue();

            CounterOrder newCounterOrder = new CounterOrder();
            newCounterOrder.setPlatformOrderNo(platformOrderNo);
            newCounterOrder.setCounterNo(counterNo);
            newCounterOrder.setTotalPaid(0);
            newCounterOrder.setActuallyPaid(0);
            newCounterOrder.setCounterOrderStat((byte) 20);
            newCounterOrder.setDisbursementStat((byte) 0);

            CounterOrder savedCounterOrder = counterOrderRepository.save(newCounterOrder);
            Integer counterOrderNo = savedCounterOrder.getCounterOrderNo();

            int counterTotalPaid = 0;

            for (var product : productList) {
                CounterOrderDetail newCounterOrderDetail = new CounterOrderDetail();

                CounterOrderDetailId id = new CounterOrderDetailId();
                id.setCounterOrderNo(counterOrderNo);
                id.setProductNo(product.getProductNo());

                newCounterOrderDetail.setId(id);
                newCounterOrderDetail.setAmount(product.getAmount());
                newCounterOrderDetail.setOrderDetailStat((byte) 20);

                counterOrderDetailRepository.save(newCounterOrderDetail);

                counterTotalPaid += product.getProductPrice() * product.getAmount();
            }

            savedCounterOrder.setTotalPaid(counterTotalPaid);
            savedCounterOrder.setActuallyPaid(counterTotalPaid);

            counterOrderRepository.save(savedCounterOrder);
        }

        // TODO: 清空購物車
    }
}
