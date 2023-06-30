package com.jamigo;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
            throws Exception {
		Enumeration<String> aaa = req.getSession().getAttributeNames();
		while (aaa.hasMoreElements()) {
			System.out.println(aaa.nextElement());
		}
		System.out.println("session id 為" + req.getSession().getId());
		if(req.getSession().getAttribute("counter") == null) {
			System.out.println("session中的platform為null");
			// 在進入處理器方法之前執行的邏輯
			// 此處可以將當前頁面的 URL 存儲到會話中
//			String currentUrl = req.getRequestURL().toString();
//			req.getSession().setAttribute("currentUrl", currentUrl);
			resp.sendRedirect("/Jamigo/platform/login/admin_login.html");
			return false;
		}
		
<<<<<<< HEAD:src/main/java/com/jamigo/AdminLoginInterceptor.java
		System.out.println("session中的platform為true");
=======
>>>>>>> coco:src/main/java/com/jamigo/platform/platformLogin/filter/AdminLoginInterceptor.java
		return true; // 返回 true 表示繼續執行後續的處理器和攔截器			
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler,
            Exception ex) throws Exception {
    }
}
