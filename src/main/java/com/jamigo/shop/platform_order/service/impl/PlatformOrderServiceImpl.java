package com.jamigo.shop.platform_order.service.impl;

import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.cart.service.CartService;
import com.jamigo.shop.platform_order.dto.MemberDataForCheckoutDTO;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.member.member_level.dao.MemberLevelDetailRepository;
import com.jamigo.member.member_level.model.MemberLevelDetail;
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
import com.jamigo.shop.product_picture.service.ProductPictureService;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
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
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductPictureService productPictureService;


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
    public Map<String, List<CartDTO>> getCartInfoByMemberNo(Integer memberNo) {

        List<CartDTO> cartDTOList = cartService.findAllCartItem(memberNo);

        return cartDTOList.stream()
                .collect(Collectors.groupingBy(CartDTO::getCounterName));
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

    @Async
    @Override
    public String createPlatformOrder(PlatformOrder newPlatformOrder) {

        Integer memberNo = newPlatformOrder.getMemberNo();

        // 透過 JSON 資料中的會員編號，取得該會員的購物車資料
        List<CartDTO> cartDTOList = cartService.findAllCartItem(memberNo);

        // 價錢計算必須放在後端
        // 計算原總金額
        int totalPaid = cartDTOList.stream()
                .mapToInt(cartItem -> cartItem.getProductPrice() * cartItem.getQuantity())
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

        Map<Integer, List<CartDTO>> cartMap = cartDTOList.stream()
                .collect(Collectors.groupingBy(CartDTO::getCounterNo));

        for (var entry : cartMap.entrySet()) {

            Integer counterNo = entry.getKey();
            List<CartDTO> productList = entry.getValue();

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
                newCounterOrderDetail.setAmount(product.getQuantity());
                newCounterOrderDetail.setOrderDetailStat(savedPlatformOrder.getPlatformOrderStat());

                counterOrderDetailRepository.save(newCounterOrderDetail);

                counterTotalPaid += product.getProductPrice() * product.getQuantity();
            }

            savedCounterOrder.setTotalPaid(counterTotalPaid);
            savedCounterOrder.setActuallyPaid(counterTotalPaid);

            counterOrderRepository.save(savedCounterOrder);
        }

        // 刪除會員所有存放在購物車的商品
//        for (var cartItem : cartDTOList) {
//            cartService.deleteOneInCart(cartItem, memberNo);
//        }

        try {
            sendEmail(savedPlatformOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (savedPlatformOrder.getPaymentMethod() == 1)
            return ecpayCheckout(savedPlatformOrder);

        return null;
    }

    public String ecpayCheckout(PlatformOrder newPlatformOrder) {

        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);

        Timestamp timestamp = newPlatformOrder.getOrderTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strTimestamp = sdf.format(timestamp);

        AllInOne all = new AllInOne("");

        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(uuId);
        obj.setMerchantTradeDate(strTimestamp);
        obj.setTotalAmount(String.valueOf(newPlatformOrder.getActuallyPaid()));

        obj.setTradeDesc("Jamigo Mall 購物測試");
        obj.setItemName("Jamigo Mall 商品");
        String orderResultURL = "http://localhost:8080/Jamigo/shop/platform_order/" + newPlatformOrder.getPlatformOrderNo().toString() + "/paidResult";
        obj.setOrderResultURL(orderResultURL);
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
        obj.setClientBackURL("http://localhost:8080/Jamigo/shop/main_page/shopping_main_page.html");
        String form = all.aioCheckOut(obj, null);

        return form;
    }

    public void changePaidStat(Integer platformOrderNo, String formData) {

        Map<String, String> map = new HashMap<String, String>();

        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            map.put(pair.substring(0, idx), pair.substring(idx + 1));
        }

        if ("1".equals(map.get("RtnCode"))) {
            PlatformOrder platformOrder = platformOrderRepository.findById(platformOrderNo).orElse(null);

            if (platformOrder != null) {
                platformOrder.setPaymentStat((byte) 1);
                platformOrder.setPlatformOrderStat((byte) 20);
                platformOrderRepository.save(platformOrder);

                List<CounterOrder> counterOrderList = counterOrderRepository.findAllByPlatformOrderNo(platformOrderNo);

                for (var counterOrder : counterOrderList) {

                    List<CounterOrderDetail> counterOrderDetailList = counterOrderDetailRepository.findAllByIdCounterOrderNo(counterOrder.getCounterOrderNo());

                    for (var counterOrderDetail : counterOrderDetailList) {
                        counterOrderDetail.setOrderDetailStat((byte) 20);
                        counterOrderDetailRepository.save(counterOrderDetail);
                    }

                    counterOrder.setCounterOrderStat((byte) 20);
                    counterOrderRepository.save(counterOrder);
                }
            }
        }
    }

    public void sendEmail(PlatformOrder platformOrder) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("[Jamigo Mall 線上商城] 您的訂單正在準備中");
        helper.setFrom("jamigo.contact@gmail.com", "Jamigo Mall");
        helper.setTo(platformOrder.getBuyerEmail());

        Map<String, byte[]> images = new HashMap<>();
        String emailContent = getEmailContent(platformOrder, images);
        helper.setText(emailContent, true);

        // 將所有的圖片添加到電子郵件中
        for (Map.Entry<String, byte[]> image : images.entrySet()) {
            String id = image.getKey();
            byte[] bytes = image.getValue();
            helper.addInline(id, new ByteArrayResource(bytes), "image/gif");
        }

        javaMailSender.send(mimeMessage);
    }

    public String getEmailContent(PlatformOrder platformOrder, Map<String, byte[]> images) throws Exception {

        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();

        // 將你的資料添加到模型中
        Map<String, CounterOrderForPlatformOrderDTO> orderDetailMap = getPlatformOrderDetailById(platformOrder.getPlatformOrderNo());
        model.put("orderDetailMap", orderDetailMap);
        model.put("platformOrder", platformOrder);

        // 遍歷所有的訂單，獲取每個商品的圖片
        for (CounterOrderForPlatformOrderDTO counterOrder : orderDetailMap.values()) {
            for (ProductDetailForPlatformOrderDTO product : counterOrder.getProduct()) {
                byte[] image = productPictureService.getFirstProductPicByProductNo(product.getProductNo());
                images.put("image" + product.getProductNo(), image);
            }
        }

        configuration.getTemplate("order_confirm.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    @Override
    public List<PlatformOrder> getAllPlatformOrderByMemberNo(Integer memberNo) {
        return platformOrderRepository.findAllByMemberNo(memberNo);
    }


}
