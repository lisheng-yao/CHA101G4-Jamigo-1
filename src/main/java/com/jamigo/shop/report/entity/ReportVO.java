package com.jamigo.shop.report.entity;

import java.sql.Array;
import java.util.Date;

public class ReportVO implements java.io.Serializable {

	private Integer productNo;

	private Integer memberNo;

	private String reportContent;

	private Integer reportStat;

	private Date reportTime;

	private String reportResponse;
	
	private Date responseTime;

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



	
	
}
