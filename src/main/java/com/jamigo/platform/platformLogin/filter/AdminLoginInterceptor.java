package com.jamigo.platform.platformLogin.filter;

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
		Object attribute = req.getSession().getAttribute("user");
		if(attribute == null) {
			// 在進入處理器方法之前執行的邏輯
			// 此處可以將當前頁面的 URL 存儲到會話中
//			String currentUrl = req.getRequestURL().toString();
//			req.getSession().setAttribute("currentUrl", currentUrl);
			resp.sendRedirect("/Jamigo/platform/login/admin_login.html");
			return false;
		}
		
		return true; // 返回 true 表示繼續執行後續的處理器和攔截器			
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler,
            ModelAndView modelAndView) throws Exception {
        // 在執行完處理器方法之後，返回 ModelAndView 之前執行的邏輯
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler,
            Exception ex) throws Exception {
        // 在整個請求完成之後執行的邏輯
    }
}
