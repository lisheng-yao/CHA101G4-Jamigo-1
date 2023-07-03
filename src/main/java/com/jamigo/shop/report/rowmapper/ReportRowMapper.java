package com.jamigo.shop.report.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jamigo.shop.report.entity.ReportVO;

public class ReportRowMapper implements RowMapper {

    @Override
    public ReportVO mapRow(ResultSet rs, int rowNum) throws SQLException {

        ReportVO reportVO = new ReportVO();

        reportVO.setProductName(rs.getString("productName"));
        reportVO.setMemberName(rs.getString("memberName"));
        reportVO.setReportContent(rs.getString("reportContent"));
        reportVO.setReportStat(rs.getInt("reportStat"));
        reportVO.setReportTime(rs.getTimestamp("reportTime"));
        reportVO.setReportResponse(rs.getString("reportResponse"));
        reportVO.setReportImage(rs.getBytes("reportImage"));
        reportVO.setResponseTime(rs.getTimestamp("responseTime"));
        reportVO.setReportNumber(rs.getInt("reportNumber"));
        reportVO.setProductNo(rs.getInt("productNo"));
        reportVO.setMemberNo(rs.getInt("memberNo"));


        return reportVO;


    }
}

