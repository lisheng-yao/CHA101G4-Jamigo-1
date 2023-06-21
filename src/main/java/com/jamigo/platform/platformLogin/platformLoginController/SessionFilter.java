package com.jamigo.platform.platformLogin.platformLoginController;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jamigo.platform.platformLogin.platformLoginModel.AdministratorVO;

public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName() == "JSESSIONID") {
					HttpSession session = req.getSession();
					AdministratorVO admin = (AdministratorVO)session.getAttribute("user");
					String adminName = admin.getAdminName();
					Integer adminNo = admin.getAdminNo();
					
					
				}
			}
		}
	}

}
