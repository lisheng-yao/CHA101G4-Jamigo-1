package com.jamigo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getSession().getAttribute("counter") == null) {
//			System.out.println("session中的counter為null");
			response.sendRedirect(request.getContextPath() + "/counter/login/counter_login.html");
			return false;
		}
		// System.out.println("session中的uid為= " +
		// request.getSession().getAttribute("uid"));
//		System.out.println("session中的counter為ok");
		return true;
	}
}
