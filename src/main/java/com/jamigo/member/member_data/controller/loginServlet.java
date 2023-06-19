package com.jamigo.member.member_data.controller;

import com.jamigo.member.member_data.Service.impl.MemberServiceImpl;
import com.jamigo.member.member_data.entity.MemberData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.jamigo.member.member_data.util.CommonUtil.json2Pojo;
import static com.jamigo.member.member_data.util.CommonUtil.writePojo2Json;

@WebServlet("/login/login")
public class loginServlet extends HttpServlet {
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

        memberData = SERVICE.login(memberData);
        if (memberData.isSuccessful()) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            final HttpSession session = request.getSession();
            session.setAttribute("loggedin", true);
            session.setAttribute("memberData", memberData);
        }
        writePojo2Json(response, memberData);
    }
}
