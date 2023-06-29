package com.jamigo.shop.platform_order.service.impl;

import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.shop.platform_order.dto.MemberDataForCheckoutDTO;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.member.member_level.dao.MemberLevelDetailRepository;
import com.jamigo.member.member_level.model.MemberLevelDetail;
import com.jamigo.shop.platform_order.dto.CartForCheckoutDTO;
import com.jamigo.shop.counter_order.entity.CounterOrder;
import com.jamigo.shop.counter_order.repo.CounterOrderRepository;
import com.jamigo.shop.counter_order_detail.entity.CounterOrderDetail;
import com.jamigo.shop.counter_order_detail.entity.CounterOrderDetailId;
import com.jamigo.shop.counter_order_detail.repo.CounterOrderDetailRepository;
import com.jamigo.shop.platform_order.dto.CounterOrderForPlatformOrderDTO;
import com.jamigo.shop.platform_order.dto.PlatformOrderDetailDTO;
import com.jamigo.shop.platform_order.dto.ProductDetailForPlatformOrderDTO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.repo.PlatformOrderRepository;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlatformOrderServiceImpl implements PlatformOrderService {

    @Autowired
    private MemberDataDAO memberDataDAO;
    @Autowired
    private PlatformOrderRepository platformOrderRepository;
    @Autowired
    private CounterOrderRepository counterOrderRepository;
    @Autowired
    private CounterOrderDetailRepository counterOrderDetailRepository;
    @Autowired
    private MemberLevelDetailRepository memberLevelDetailRepository;
    @Autowired
    private Configuration configuration;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public MemberDataForCheckoutDTO getMemberDataForCheckout(Integer memberNo) {

        // 取得該會員的全部資料
        MemberData memberData = memberDataDAO.selectById(memberNo);

        if (memberData != null) {

            // 取得該會員的會員等級編號，透過會員等級編號查詢會員等級資訊
            MemberLevelDetail memberLevelDetail = memberLevelDetailRepository.findById(Integer.valueOf(memberData.getLevelNo())).orElse(null);

            if (memberLevelDetail != null) {

                // 將結帳會用到的資料封裝起來
                MemberDataForCheckoutDTO memberDataForCheckoutDTO = new MemberDataForCheckoutDTO();

                memberDataForCheckoutDTO.setMemberName(memberData.getMemberName());
                memberDataForCheckoutDTO.setMemberPhone(memberData.getMemberPhone());
                memberDataForCheckoutDTO.setMemberEmail(memberData.getMemberEmail());
                memberDataForCheckoutDTO.setMemberAddress(memberData.getMemberAddress());
                memberDataForCheckoutDTO.setMemberLevelDetail(memberLevelDetail);

                return memberDataForCheckoutDTO;
            } else
                return null;
        } else
            return null;
    }


    @Override
    public Map<String, List<CartForCheckoutDTO>> getCartInfoByMemberNo(Integer memberNo) {

        List<CartForCheckoutDTO> cartList = platformOrderRepository.getCartInfoByMemberNo(memberNo);

        return cartList.stream()
                .collect(Collectors.groupingBy(CartForCheckoutDTO::getCounterName));
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
    public Map<String, CounterOrderForPlatformOrderDTO> getPlatformOrderDetailById(Integer platformOrderNo) {

        List<PlatformOrderDetailDTO> orderDetailList = platformOrderRepository.getOrderDetailByPlatformOrderNo(platformOrderNo);

        Map<String, CounterOrderForPlatformOrderDTO> orderDetailMap = new HashMap<>();

        for (PlatformOrderDetailDTO detail : orderDetailList) {
            String counterName = detail.getCounterName();
            CounterOrderForPlatformOrderDTO counterOrder = orderDetailMap.get(counterName);

            if (counterOrder == null) {
                counterOrder = new CounterOrderForPlatformOrderDTO();
                counterOrder.setDisbursementStat(detail.getDisbursementStat());
                orderDetailMap.put(counterName, counterOrder);
            }

            ProductDetailForPlatformOrderDTO productDetail = new ProductDetailForPlatformOrderDTO();
            productDetail.setProductNo(detail.getProductNo());
            productDetail.setProductName(detail.getProductName());
            productDetail.setProductPrice(detail.getProductPrice());
            productDetail.setAmount(detail.getAmount());
            productDetail.setOrderDetailStat(detail.getOrderDetailStat());

            counterOrder.getProduct().add(productDetail);
        }

        return orderDetailMap;
    }


    @Override
    public void createPlatformOrder(PlatformOrder newPlatformOrder) {

        // 透過 JSON 資料中的會員編號，取得該會員的購物車資料
        List<CartForCheckoutDTO> cartList = platformOrderRepository.getCartInfoByMemberNo(newPlatformOrder.getMemberNo());

        // 價錢計算必須放在後端
        // 計算原總金額
        int totalPaid = cartList.stream()
                .mapToInt(cartItem -> cartItem.getProductPrice() * cartItem.getAmount())
                .sum();

        // 計算訂單實付金額
        int actuallyPaid = totalPaid - newPlatformOrder.getTotalCoupon() - newPlatformOrder.getTotalPoints();

        // 查詢會員等級資訊，計算回饋點數時會用到
        MemberData memberData = memberDataDAO.selectById(newPlatformOrder.getMemberNo());
        MemberLevelDetail memberLevelDetail = memberLevelDetailRepository.findById(Integer.valueOf(memberData.getLevelNo())).orElse(null);
        // 計算回饋點數
        int levelFeedback = (memberLevelDetail != null) ? memberLevelDetail.getLevelFeedback() : 1;
        int rewardPoints = Math.round(actuallyPaid / 10.0f * levelFeedback);


        newPlatformOrder.setTotalPaid(totalPaid);
        newPlatformOrder.setActuallyPaid(actuallyPaid);
        newPlatformOrder.setRewardPoints(rewardPoints);

        switch (newPlatformOrder.getPaymentMethod()) {

            case 1:
                newPlatformOrder.setPlatformOrderStat((byte) 10);
                newPlatformOrder.setPaymentStat((byte) 0);
                break;

            case 2:
                newPlatformOrder.setPlatformOrderStat((byte) 20);
                newPlatformOrder.setPaymentStat((byte) 0);
        }

        newPlatformOrder.setOrderTime(new Timestamp(System.currentTimeMillis()));


        // 更新訂單資訊
        PlatformOrder savedPlatformOrder = platformOrderRepository.save(newPlatformOrder);


        // 拆單
        // 取得平台訂單編號
        Integer platformOrderNo = savedPlatformOrder.getPlatformOrderNo();

        Map<Integer, List<CartForCheckoutDTO>> cartMap = cartList.stream()
                .collect(Collectors.groupingBy(CartForCheckoutDTO::getCounterNo));

        for (var entry : cartMap.entrySet()) {

            Integer counterNo = entry.getKey();
            List<CartForCheckoutDTO> productList = entry.getValue();

            CounterOrder newCounterOrder = new CounterOrder();
            newCounterOrder.setPlatformOrderNo(platformOrderNo);
            newCounterOrder.setCounterNo(counterNo);
            newCounterOrder.setTotalPaid(0);  // 先設定櫃位訂單的 totalPaid 為 0，等等再更新
            newCounterOrder.setActuallyPaid(0);
            newCounterOrder.setCounterOrderStat(savedPlatformOrder.getPlatformOrderStat());
            newCounterOrder.setDisbursementStat((byte) 0);

            CounterOrder savedCounterOrder = counterOrderRepository.save(newCounterOrder);

            // 取得櫃位訂單編號
            Integer counterOrderNo = savedCounterOrder.getCounterOrderNo();

            int counterTotalPaid = 0;

            for (var product : productList) {
                CounterOrderDetail newCounterOrderDetail = new CounterOrderDetail();

                CounterOrderDetailId id = new CounterOrderDetailId();
                id.setCounterOrderNo(counterOrderNo);
                id.setProductNo(product.getProductNo());

                newCounterOrderDetail.setId(id);
                newCounterOrderDetail.setAmount(product.getAmount());
                newCounterOrderDetail.setOrderDetailStat(savedPlatformOrder.getPlatformOrderStat());

                counterOrderDetailRepository.save(newCounterOrderDetail);

                counterTotalPaid += product.getProductPrice() * product.getAmount();
            }

            savedCounterOrder.setTotalPaid(counterTotalPaid);
            savedCounterOrder.setActuallyPaid(counterTotalPaid);

            counterOrderRepository.save(savedCounterOrder);
        }

//        try {
//            sendEmail();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        // TODO: 清空購物車
    }

    public void sendEmail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 使用支持 "multipart" 的 MimeMessageHelper
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("Welcome To SpringHow.com");
        helper.setTo("hao.4f31702@gmail.com");
        String emailContent = getEmailContent();
        helper.setText(emailContent, true);

        // 取得商品編號為1的商品的首張圖片
//        byte[] image = getFirstProductPicByProductNo(1);
//        if (image != null && image.length > 0) {
//            // 添加內嵌的圖片
//            helper.addInline("productPicture", new ByteArrayResource(image), "image/gif");
//        }
        javaMailSender.send(mimeMessage);
    }

    public String getEmailContent() throws Exception {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
//        model.put("user", user);
        configuration.getTemplate("order_confirm.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
