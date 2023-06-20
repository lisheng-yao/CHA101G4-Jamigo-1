package com.jamigo.shop.report.dao.impl;

import com.jamigo.shop.report.dao.ReportDAO;
import com.jamigo.shop.report.dto.ReportRequest;
import com.jamigo.shop.report.entity.ReportVO;
import com.jamigo.shop.report.rowmapper.ReportRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReportDAOimpl implements ReportDAO {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterdbcTemplate;

    @Override
    public String insertOne(ReportRequest reportRequest) {
        String sql = "INSERT INTO report (productNo,memberNo,reportContent,reportStat,reportTime" +
                ") VALUES(:productNo,:memberNo,:reportContent,:reportStat,:reportTime)";

        Map<String, Object> map = new HashMap<>();

        map.put("productNo", reportRequest.getProductNo());
        map.put("memberNo", reportRequest.getMemberNo());
        map.put("reportContent", reportRequest.getReportContent());
        map.put("reportStat", reportRequest.getReportStat());

        Date now = new Date();
        map.put("reportTime", now);

        namedParameterdbcTemplate.update(sql, new MapSqlParameterSource(map));

        return "新增成功";
    }
    @Override
    public List<ReportVO> getAllReports() {
        String sql = "SELECT (SELECT productName FROM product WHERE productNo = report.productNo) AS productName," +
                "(SELECT memberName FROM member_data WHERE memberNo = report.memberNo ) AS memberName ,reportContent," +
                "reportStat,reportTime,reportResponse,responseTime FROM report";

    Map<String,Object> map = new HashMap<>();

    List<ReportVO> list = namedParameterdbcTemplate.query(sql,map,new ReportRowMapper());

        return list;
    }
    @Override
    public String updateOne(ReportRequest reportRequest) {
        return null;
    }

    @Override
    public List<ReportVO> getReportByMember(Integer memberNo) {
        return null;
    }

    @Override
    public ReportVO getReportByPrimaryKey(Integer memberNo, Integer productNo) {
        return null;
    }


}
