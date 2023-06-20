package com.jamigo.shop.report.dao;

import java.util.List;

import com.jamigo.shop.report.dto.ReportRequest;
import com.jamigo.shop.report.entity.ReportVO;

public interface ReportDAO {

	String insertOne(ReportRequest reportRequest);
	
	String updateOne(ReportRequest reportRequest);

	List<ReportVO> getReportByMember(Integer memberNo); 

	ReportVO getReportByPrimaryKey(Integer memberNo, Integer productNo);
	
	List<ReportVO> getAllReports();

}
