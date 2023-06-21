package com.jamigo.shop.report.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamigo.shop.report.dto.ReportRequest;
import com.jamigo.shop.report.entity.ReportVO;
import org.springframework.stereotype.Component;


@Component
public interface ReportDAO{

	String insertOne(ReportRequest reportRequest);
	List<ReportVO> getAllReports();
	String updateOne(ReportRequest reportRequest);

	List<ReportVO> getReportByMember(Integer memberNo);

	ReportVO getReportByPrimaryKey(Integer memberNo, Integer productNo);



}
