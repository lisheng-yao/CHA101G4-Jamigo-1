package com.jamigo.member.member_data.controller;

import com.jamigo.member.member_data.Service.MemberService;
import com.jamigo.member.member_data.entity.MemberData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jamigo.member.member_data.util.CommonUtil.json2Pojo;
import static com.jamigo.member.member_data.util.CommonUtil.writePojo2Json;

@WebServlet("/member/login/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private MemberService SERVICE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json; charset=utf-8");
        MemberData memberData = json2Pojo(request, MemberData.class);


        if (memberData == null) {
            memberData = new MemberData();
            memberData.setMessage("無會員資訊");
            memberData.setSuccessful(false);
            writePojo2Json(response, memberData);
            return;
        }
        memberData = SERVICE.register(memberData);
        writePojo2Json(response, memberData);
    }

}
