package com.jamigo.platform.platformLogin.platformLoginModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<AdministratorVO, Integer> {
	
	AdministratorVO findByadminNameAndAdminPassword(String adminName, String adminPassword);
	
}
