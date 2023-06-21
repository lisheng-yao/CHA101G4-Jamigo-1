package com.jamigo.platform.platformLogin.platformLoginController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
