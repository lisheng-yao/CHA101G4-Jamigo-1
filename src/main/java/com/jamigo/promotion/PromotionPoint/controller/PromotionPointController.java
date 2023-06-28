package com.jamigo.promotion.PromotionPoint.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.promotion.PromotionPoint.Entity.PromotionPoint;
import com.jamigo.promotion.PromotionPoint.Service.PromotionPointService;
import com.jamigo.promotion.PromotionType.Entity.Promotion;
import com.jamigo.promotion.PromotionType.Service.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class PromotionPointController {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PromotionPointService promotionPointService;
    @Autowired
    private PromotionTypeService promotionTypeService;

    @PostMapping("promotion/promotion/editPromotionPoint")
    public PromotionPoint editPromotion(@RequestBody String promotionPointRequest) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來但是 不是key：vlaue取出並刪除
            JsonNode jsonNode = objectMapper.readTree(promotionPointRequest);//先拿到jsonnode
            String promotionPic4json = jsonNode.get("promotionPic4json").asText();//拿到 想取出的欄位
            byte[] PromotionPic = null;
            if (promotionPic4json != null) { //轉回byte[]
                PromotionPic = Base64.getDecoder().decode(promotionPic4json);
            }

            ObjectNode objectNode = (ObjectNode) jsonNode;//轉型 才能刪除
            objectNode.remove("promotionPic4json");//刪除
            objectNode.put("promotionPic", PromotionPic);
            PromotionPoint promotionPointa = objectMapper.convertValue(objectNode, PromotionPoint.class);//刪完後再包裝回去成PromotionPoint物件

            PromotionPoint promotionPoint = promotionPointService.edit(promotionPointa);
            return promotionPoint;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }

    ;

    @PostMapping("promotion/promotion/newPromotionPoint")
    public PromotionPoint newPromotion(@RequestBody String promotionPointRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來但是 不是key：vlaue取出並刪除
            JsonNode jsonNode = objectMapper.readTree(promotionPointRequest);//先拿到jsonnode
            String promotionPic4json = jsonNode.get("promotionPic4json").asText();//拿到 想取出的欄位
            byte[] PromotionPic = null;
            if (promotionPic4json != null) { //轉回byte[]
                PromotionPic = Base64.getDecoder().decode(promotionPic4json);
            }

            ObjectNode objectNode = (ObjectNode) jsonNode;//轉型 才能刪除
            objectNode.remove("promotionPic4json");//刪除
            objectNode.put("promotionPic", PromotionPic);
            PromotionPoint promotionPointa = objectMapper.convertValue(objectNode, PromotionPoint.class);//刪完後再包裝回去成PromotionPoint物件

            PromotionPoint promotionPoint = promotionPointService.add(promotionPointa);
            System.out.println("新增controller");
            return promotionPoint;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }

    ;

    @GetMapping("promotion/promotion/getAllPromotionPoint")
    public List<PromotionPoint> findAllPromotionPoint() {
        return promotionPointService.findAll();
    }

    @GetMapping("promotion/promotion/getAllPromotion2")
    public List<Promotion> findAllPromotion() {
        return promotionTypeService.findAll();
    }

    @PostMapping("promotion/promotion/deletePromotionPoint")
    public Boolean deletePromotion(@RequestBody PromotionPoint PromotionPointRequest) {
        Integer PromotionPointNo = PromotionPointRequest.getPromotionPointNo();
        Boolean deletesucceed = promotionPointService.remove(PromotionPointNo);
        return deletesucceed;
    }

    ;
}






