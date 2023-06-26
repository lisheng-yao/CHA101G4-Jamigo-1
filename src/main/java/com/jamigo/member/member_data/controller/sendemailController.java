package com.jamigo.member.member_data.controller;

import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.member.member_data.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class sendemailController {
    @Autowired
    private SendEmail sendEmail;

    @PostMapping("/member/login/memberemail")
    public void checkemail(@RequestBody MemberData memberData) {
         System.out.println(memberData.getMemberEmail());
        sendEmail.sendMail(memberData.getMemberEmail());
    }


}
