package com.jamigo.platform.platformLogin.platformLoginController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.platform.platformLogin.platformLoginModel.AdministratorService;
import com.jamigo.platform.platformLogin.platformLoginModel.AdministratorVO;

@RestController
@RequestMapping("/administratorCheck")
public class AdministratorFilter {
	
	@Autowired
	AdministratorService service;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AdministratorVO admin, HttpServletRequest req ) {

		AdministratorVO adminCheck = service.findByName(admin.getAdminName(), admin.getAdminPassword());
		
		if(adminCheck != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", adminCheck);
			session.getAttribute("user");
			 return new ResponseEntity<>("登入成功", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("登入失敗", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getAdmin")
	public AdministratorVO getAdmin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (AdministratorVO)session.getAttribute("user");
	}
	
	@GetMapping("*/counter/*")
	public String checkSession(@CookieValue("JSESSIONID") String sessionId) {
		
		if(sessionId == null) {
			return "redirect:admin_login";
		}
		return "redirect:/activity/activity_order/activity_orderCtrl.html";
		
	}
	
}
