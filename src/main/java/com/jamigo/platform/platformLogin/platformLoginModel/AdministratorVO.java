package com.jamigo.platform.platformLogin.platformLoginModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "administrator")
public class AdministratorVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminNo;
	private String adminPassword;
	private String adminName;
	private Byte adminStat;
	private String adminEmail;
	private byte[] adminPic;
	
	public AdministratorVO() {}

	public AdministratorVO(Integer adminNo, String adminPassword, String adminName, Byte adminStat, String adminEmail,
			byte[] adminPic) {
		super();
		this.adminNo = adminNo;
		this.adminPassword = adminPassword;
		this.adminName = adminName;
		this.adminStat = adminStat;
		this.adminEmail = adminEmail;
		this.adminPic = adminPic;
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Byte getAdminStat() {
		return adminStat;
	}

	public void setAdminStat(Byte adminStat) {
		this.adminStat = adminStat;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public byte[] getAdminPic() {
		return adminPic;
	}

	public void setAdminPic(byte[] adminPic) {
		this.adminPic = adminPic;
	}

}
