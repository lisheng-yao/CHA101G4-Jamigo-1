package com.jamigo.shop.report.service.impl;

import com.jamigo.shop.report.dao.ReportDAO;
import com.jamigo.shop.report.dto.ReportRequest;
import com.jamigo.shop.report.entity.ReportVO;
import com.jamigo.shop.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportServiceimpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;


    @Override
    public String insertOne(ReportRequest reportRequest) {
        return reportDAO.insertOne(reportRequest);
    }
    @Override
    public List<ReportVO> getAllReports() {
        return reportDAO.getAllReports();
    }
    @Override
    public ReportVO getReportByPrimaryKey(Integer memberNo, Integer productNo) {
        return null;
    }

    @Override
    public List<ReportVO> getReportByMember(Integer memberNo) {
        return null;
    }


}
