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
    public List<ReportVO> getReportByPrimaryKey(Integer memberNo, Integer productNo) {
        return reportDAO.getReportByPrimaryKey(memberNo,productNo);
    }

    @Override
    public String updateResponse(ReportRequest reportRequest) {
        return reportDAO.updateOne(reportRequest);
    }

    @Override
    public List<ReportVO> getReportByMember(Integer memberNo) {
        return reportDAO.getReportByMember(memberNo);
    }


}
