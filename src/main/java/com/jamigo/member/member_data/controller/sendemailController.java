package com.jamigo.member.member_data.controller;

import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.member.member_data.util.SendEmail;
import com.jamigo.member.member_data.util.SendEmail4forgot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class sendemailController {
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private SendEmail4forgot sendEmail4forgot;

    @PostMapping("/member/login/memberemail")
    public void checkemail(@RequestBody MemberData memberData) {
        sendEmail.sendMail(memberData.getMemberEmail());
    }

    @PostMapping("/member/login/forgot")
    public void forgotmail(@RequestBody MemberData memberData) {
        sendEmail4forgot.sendMail(memberData);
    }


}
