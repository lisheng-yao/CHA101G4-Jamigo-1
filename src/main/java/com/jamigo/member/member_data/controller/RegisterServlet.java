package com.jamigo.member.member_data.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jamigo.member.member_data.Service.MemberService;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.member.member_data.util.CommonUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class RegisterServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private MemberService SERVICE;
    @Autowired
    private CommonUtil2 commonUtil2;

    @PostMapping("/member/login/register")
    public MemberData dopost(@RequestBody String jsonBody) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來但是不是memberdata的key：vlaue取出並刪除
            JsonNode jsonNode = objectMapper.readTree(jsonBody);//先拿到jsonnode
            String email_verificationValue = jsonNode.get("email_verificationValue").asText();//拿到 想取出的欄位
            ObjectNode objectNode = (ObjectNode) jsonNode;//轉型 才能刪除
            objectNode.remove("email_verificationValue");//刪除
            MemberData memberData = objectMapper.convertValue(objectNode, MemberData.class);//刪完後再包裝回去成memberdata物件



            if (memberData == null) {
                memberData = new MemberData();
                memberData.setMessage("無會員資訊");
                memberData.setSuccessful(false);
                return null;
            }
            String randoncode = commonUtil2.getRandonCode(memberData.getMemberEmail());

            if (!randoncode.equals(email_verificationValue)) {
                memberData.setMessage("驗證碼錯誤");
                memberData.setSuccessful(false);
                return null;
            }

            memberData = SERVICE.register(memberData);
            return memberData;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }
}
