package com.jamigo.platform.platformLogin.platformLoginController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.jamigo.platform.platformLogin.platformLoginModel.AdministratorDTO;
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
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AdministratorVO admin, HttpServletRequest req, HttpServletResponse resp) {

		AdministratorVO adminCheck = service.findByName(admin.getAdminName(), admin.getAdminPassword());
		
		if(adminCheck != null) {
			HttpSession session = req.getSession();
			session.setAttribute("counter", adminCheck);
//			String currentUrl = (String)req.getSession().getAttribute("currentUrl");
			AdministratorDTO dto = new AdministratorDTO();
			dto.setAdminNo(adminCheck.getAdminNo());;
			dto.setAdminName(adminCheck.getAdminName());
			return ResponseEntity.ok(dto);
		} else {
			return new ResponseEntity<String>("登入失敗", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(SessionStatus sessionStatus, HttpServletResponse resp) {
		sessionStatus.setComplete();
		return new ResponseEntity<>("登出", HttpStatus.OK);
	}
	
}
