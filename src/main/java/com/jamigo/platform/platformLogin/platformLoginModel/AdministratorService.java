package com.jamigo.platform.platformLogin.platformLoginModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
	
	@Autowired
	AdministratorRepository repository;
	
	public AdministratorVO findByName(String adminName, String adminPassword) {
		return repository.findByadminNameAndAdminPassword(adminName, adminPassword);
	}
	
	public List<AdministratorVO> getAll() {
		return repository.findAll();
	}
	
	public AdministratorVO getById(Integer id) {
		Optional<AdministratorVO> optional = repository.findById(id);
		return optional.get();
	}
}
