package com.jamigo.platform.platformLogin.platformLoginController;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.jamigo.platform.platformLogin.platformLoginModel.AdministratorService;
import com.jamigo.platform.platformLogin.platformLoginModel.AdministratorVO;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	AdministratorService service;
	
	@GetMapping("/getAll")
	public List<AdministratorVO> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/getById/{id}")
	public AdministratorVO getById(@PathVariable Integer id) {
		return service.getById(id);
	}

	@PostMapping("/getUser")
	public AdministratorVO getUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		AdministratorVO administratorVO = (AdministratorVO)session.getAttribute("user");
		return administratorVO;
	}

	@GetMapping("/getSession")
	public String getSession(HttpServletRequest req){
		HttpSession session = req.getSession();
		return session.getId();
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AdministratorVO admin, HttpServletRequest req, HttpServletResponse resp) {

		AdministratorVO adminCheck = service.findByName(admin.getAdminName(), admin.getAdminPassword());
		
		if(adminCheck != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", adminCheck);
			session.getAttribute("user");
			// 添加cookie
			Cookie cookieId = new Cookie("JuserId", String.valueOf(adminCheck.getAdminNo()));
			Cookie cookieName = new Cookie("JuserName", adminCheck.getAdminName());
			cookieId.setPath("/"); // 設置cookie可見路徑
			cookieName.setPath("/");
//			cookieId.setPath("/Jamigo/platform/*"); // 設置cookie可見路徑
//			cookieName.setPath("/Jamigo/platform/*");
			resp.addCookie(cookieId);
			resp.addCookie(cookieName);
			 return new ResponseEntity<>("登入成功", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("登入失敗", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity logout(SessionStatus sessionStatus, HttpServletResponse resp) {
		// 刪除cookie
		Cookie cookieId = new Cookie("JuserId", ""); // 覆蓋舊的cookie
		Cookie cookieName = new Cookie("JuserName", "");
		cookieId.setMaxAge(0); // 存活時間設為0
		cookieName.setMaxAge(0);
		resp.addCookie(cookieId); // 添加cookie
		resp.addCookie(cookieName);
		
		sessionStatus.setComplete();
		return new ResponseEntity<>("登出", HttpStatus.OK);
	}
	
	
	// 未完
	@GetMapping("*/counter/*")
	public String checkSession(@CookieValue("JSESSIONID") String sessionId) {
		
		if(sessionId == null) {
			return "redirect:admin_login";
		}
		return "redirect:/activity/activity_order/activity_orderCtrl.html";
		
	}
}
