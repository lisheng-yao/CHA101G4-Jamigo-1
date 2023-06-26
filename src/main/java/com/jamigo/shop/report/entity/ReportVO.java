package com.jamigo.shop.report.entity;

import java.sql.Array;
import java.util.Date;

public class ReportVO implements java.io.Serializable {

	private Integer productNo;

	private String productName;

	private Integer memberNo;

	private String memberName;

	private String reportContent;

	private Integer reportStat;

	private Date reportTime;

	private String reportResponse;
	
	private Date responseTime;

	private Integer reportNumber;



	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getProductNo() {
		return productNo;
	}

	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Integer getReportStat() {
		return reportStat;
	}

	public void setReportStat(Integer reportStat) {
		this.reportStat = reportStat;
	}



	public String getReportResponse() {
		return reportResponse;
	}

	public void setReportResponse(String reportResponse) {
		this.reportResponse = reportResponse;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public Integer getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(Integer reportNumber) {
		this.reportNumber = reportNumber;
	}
}
