package com.jamigo.shop.report.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jamigo.shop.report.entity.ReportVO;

public class ReportRowMapper implements RowMapper<ReportVO> {

	
	
	@Override
	public ReportVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ReportVO reportVO = new ReportVO();
		
		reportVO.setProductname(rs.getString("productName"));
		reportVO.setMembername(rs.getString("memberName"));
		reportVO.setReportContent(rs.getString("reportContent"));
		reportVO.setReportStat(rs.getInt("reportStat"));
		reportVO.setReportTime(rs.getTimestamp("reportTime"));
		reportVO.setReportResponse(rs.getString("reportResponse"));
		reportVO.setResponseTime(rs.getTimestamp("responseTime"));
		
		return reportVO;
	}
	//可能要加上查詢到sql的例外處理 try..catch 或 wasnull()
	
}
