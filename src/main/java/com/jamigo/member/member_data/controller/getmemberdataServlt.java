package com.jamigo.member.member_data.controller;

import com.jamigo.member.member_data.Service.impl.MemberServiceImpl;
import com.jamigo.member.member_data.entity.MemberData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jamigo.member.member_data.util.CommonUtil.json2Pojo;
import static com.jamigo.member.member_data.util.CommonUtil.writePojo2Json;

@WebServlet("/member/member/getmemberdata")
public class getmemberdataServlt extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private MemberServiceImpl SERVICE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        MemberData memberData = json2Pojo(request, MemberData.class);
        if (memberData == null) {
            memberData = new MemberData();
            memberData.setMessage("無會員資訊");
            memberData.setSuccessful(false);
            writePojo2Json(response, memberData);
            return;
        }

        memberData = SERVICE.selectById(memberData);
        if (memberData.isSuccessful()) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            if(memberData.getMemberPic4json()!=null){
                memberData.setMemberPic4json("有圖");
            }else {
                memberData.setMemberPic4json("沒有圖");
            }
        }
        writePojo2Json(response, memberData);
    }
}
