package com.jamigo.platform.CounterCtrl.model;

import java.io.Serializable;

public class CounterCtrlDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer		counterNo;
	private Float		cutPercent;
	private String		counterAccount;
	private String		counterPassword;
	private byte		counterStat;
	
	public Integer getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(Integer counterNo) {
		this.counterNo = counterNo;
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
	public byte getCounterStat() {
		return counterStat;
	}
	public void setCounterStat(byte counterStat) {
		this.counterStat = counterStat;
	}
}
