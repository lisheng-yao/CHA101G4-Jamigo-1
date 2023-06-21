package com.jamigo.shop.report.service;

import java.util.List;

import com.jamigo.shop.report.dto.ReportRequest;
import com.jamigo.shop.report.entity.ReportVO;

public interface ReportService {

    String insertOne(ReportRequest reportRequest);

    List<ReportVO> getAllReports();

    ReportVO getReportByPrimaryKey(Integer memberNo, Integer productNo);

    List<ReportVO> getReportByMember(Integer memberNo);


}
