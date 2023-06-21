package com.jamigo.counter.counter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name="counter")
@ToString
public class Counter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer counterNo;
	private String counterName;
	private Float cutPercent;
	private String counterAccount;
	private String counterPassword;
	private Integer counterStat;
	private String counterGui;
	private Integer counterFloor;
	private String counterTel;
	private String counterPoc;
	private String counterPocPhone;
	private String counterPocAddress;
	private String counterEmail;
	private String counterBankAccount;
	private Integer counterReportCount;
	private String counterAbout;
	private byte[] counterPic;
	
	public Integer getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(Integer counterNo) {
		this.counterNo = counterNo;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public Float getCutPercent() {
		return cutPercent;
	}
	public void setCutPercent(Float cutPercent) {
		this.cutPercent = cutPercent;
	}
	public String getCounterAccount() {
		return counterAccount;
	}
	public void setCounterAccount(String counterAccount) {
		this.counterAccount = counterAccount;
	}
	public String getCounterPassword() {
		return counterPassword;
	}
	public void setCounterPassword(String counterPassword) {
		this.counterPassword = counterPassword;
	}
	public Integer getCounterStat() {
		return counterStat;
	}
	public void setCounterStat(Integer counterStat) {
		this.counterStat = counterStat;
	}
	public String getCounterGui() {
		return counterGui;
	}
	public void setCounterGui(String counterGui) {
		this.counterGui = counterGui;
	}
	public Integer getCounterFloor() {
		return counterFloor;
	}
	public void setCounterFloor(Integer counterFloor) {
		this.counterFloor = counterFloor;
	}
	public String getCounterTel() {
		return counterTel;
	}
	public void setCounterTel(String counterTel) {
		this.counterTel = counterTel;
	}
	public String getCounterPoc() {
		return counterPoc;
	}
	public void setCounterPoc(String counterPoc) {
		this.counterPoc = counterPoc;
	}
	public String getCounterPocPhone() {
		return counterPocPhone;
	}
	public void setCounterPocPhone(String counterPocPhone) {
		this.counterPocPhone = counterPocPhone;
	}
	public String getCounterPocAddress() {
		return counterPocAddress;
	}
	public void setCounterPocAddress(String counterPocAddress) {
		this.counterPocAddress = counterPocAddress;
	}
	public String getCounterEmail() {
		return counterEmail;
	}
	public void setCounterEmail(String counterEmail) {
		this.counterEmail = counterEmail;
	}
	public String getCounterBankAccount() {
		return counterBankAccount;
	}
	public void setCounterBankAccount(String counterBankAccount) {
		this.counterBankAccount = counterBankAccount;
	}
	public Integer getCounterReportCount() {
		return counterReportCount;
	}
	public void setCounterReportCount(Integer counterReportCount) {
		this.counterReportCount = counterReportCount;
	}
	public String getCounterAbout() {
		return counterAbout;
	}
	public void setCounterAbout(String counterAbout) {
		this.counterAbout = counterAbout;
	}
	public byte[] getCounterPic() {
		return counterPic;
	}
	public void setCounterPic(byte[] counterPic) {
		this.counterPic = counterPic;
	}
	
	
	

}
