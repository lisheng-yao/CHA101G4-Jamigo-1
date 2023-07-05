package com.jamigo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MemberLonginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getSession().getAttribute("memberNo"));
        if (request.getSession().getAttribute("memberNo") == null) {
            System.out.println("0000000000000000000000000");
//			System.out.println("session中的counter為null");
            response.sendRedirect(request.getContextPath() + "/member/login/login.html");
            return false;
        }
        // System.out.println("session中的uid為= " +
        // request.getSession().getAttribute("uid"));
//		System.out.println("session中的counter為ok");
        return true;
    }

}
