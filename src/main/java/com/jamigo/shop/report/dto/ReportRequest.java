package com.jamigo.shop.report.dto;


public class ReportRequest {

	

	private Integer productNo;
	
	private Integer memberNo;
	
	private String reportContent;

	private String[] reportContents; 

	private Integer reportStat;

	private String reportResponse;
	
	private String[] reportResponses;
	
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

	public String[] getReportContents() {
		return reportContents;
	}

	public void setReportContents(String[] reportContents) {
		this.reportContents = reportContents;
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

	public String[] getReportResponses() {
		return reportResponses;
	}

	public void setReportResponses(String[] reportResponses) {
		this.reportResponses = reportResponses;
	}

	

	
	
}
