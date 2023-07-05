package com.jamigo.member.member_data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class LogOut {
    @GetMapping(value = {"/frontEnd/log_out", "/member/member/log_out", "/member/center/log_out"})
    public void sigout(HttpSession session) {
        // 銷毀session中的KV
        System.out.println("清除session");
        session.removeAttribute("memberNo");
    }
}
