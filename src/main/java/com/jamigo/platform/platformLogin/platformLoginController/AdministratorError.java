//package com.jamigo.platform.platformLogin.platformLoginController;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/administratorCheck")
//public class AdministratorError implements ErrorController {
//	
//	@GetMapping("/checkSession")
//	public String checkSession(@CookieValue("JSESSIONID") String sessionId, HttpServletResponse resp) {
//		int status = resp.getStatus();
//		if(status == HttpStatus.BAD_REQUEST.value()) {
//			return "admin_login";
//		}
//		return "activity_orderCtrl";
//	}
//	
//}
