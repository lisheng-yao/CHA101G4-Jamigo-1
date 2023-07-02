package com.jamigo.platform.platformLogin.platformLoginModel;

public class AdministratorDTO {
	
	private Integer adminNo;
	private String adminName;
	
	public AdministratorDTO() {}

	public AdministratorDTO(Integer adminNo, String adminName) {
		this.adminNo = adminNo;
		this.adminName = adminName;
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}
