package com.jamigo.shop.report.dao.impl;

import com.jamigo.shop.report.dao.ReportDAO;
import com.jamigo.shop.report.dto.ReportRequest;
import com.jamigo.shop.report.entity.ReportVO;
import com.jamigo.shop.report.rowmapper.ReportRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.*;

@Component
public class ReportDAOimpl implements ReportDAO {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterdbcTemplate;

    @Override
    public String insertOne(ReportRequest reportRequest) {
        String sql = "INSERT INTO report (productNo,memberNo,reportContent,reportStat,reportTime,reportImage" +
                ") VALUES(:productNo,:memberNo,:reportContent,:reportStat,:reportTime,:reportImage)";

        String sql2 = "UPDATE product SET reportNumber = reportNumber + 1 WHERE productNo = :productNo";

        Map<String, Object> map = new HashMap<>();

        map.put("productNo", reportRequest.getProductNo());
        map.put("memberNo", reportRequest.getMemberNo());
        map.put("reportContent", reportRequest.getReportContent());
        map.put("reportStat", reportRequest.getReportStat());
        map.put("reportImage",reportRequest.getReportImage());

        Date now = new Date();
        map.put("reportTime", now);

        namedParameterdbcTemplate.update(sql, new MapSqlParameterSource(map));
        namedParameterdbcTemplate.update(sql2, new MapSqlParameterSource(map));

        return "新增成功";
    }

    @Override
    public List<ReportVO> getAllReports() {
        String sql = "SELECT (SELECT productName FROM product WHERE productNo = report.productNo) AS productName," +
                "(SELECT memberName FROM member_data WHERE memberNo = report.memberNo ) AS memberName ,reportContent," +
                "(SELECT reportNumber FROM product WHERE productNo = report.productNo) AS reportNumber," +
                "reportStat,reportTime,reportResponse,responseTime,productNo,memberNo FROM report";

        Map<String, Object> map = new HashMap<>();

        List<ReportVO> list = namedParameterdbcTemplate.query(sql, map, new ReportRowMapper());

        return list;
    }


    @Override
    public List<ReportVO> getReportByPrimaryKey(Integer memberNo, Integer productNo) {
        String sql = "SELECT (SELECT productName FROM product WHERE productNo = report.productNo) AS productName," +
                "(SELECT memberName FROM member_data WHERE memberNo = report.memberNo ) AS memberName ,reportContent,(SELECT reportNumber FROM product WHERE productNo = report.productNo) AS reportNumber," +
                "reportStat,reportTime,reportResponse,responseTime,reportImage,productNo,memberNo FROM report WHERE productNo = :productNo AND memberNo = :memberNo";


        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", memberNo);
        map.put("productNo", productNo);


        List<ReportVO> reportlist = namedParameterdbcTemplate.query(sql, map, new ReportRowMapper());

        if (!reportlist.isEmpty()) {
            return reportlist;
        } else {
            return null;
        }

    }

    @Override
    public String updateOne(ReportRequest reportRequest) {
        String sql = "UPDATE report SET reportStat = :reportStat,reportResponse = :reportResponse,responseTime = :responseTime WHERE productNo = :productNo" +
                " AND memberNo = :memberNo";

        Map<String,Object> map = new HashMap<>();
        map.put("productNo",reportRequest.getProductNo());
        map.put("memberNo",reportRequest.getMemberNo());
        map.put("reportStat",reportRequest.getReportStat());
        map.put("reportResponse",reportRequest.getReportResponse());

        Date now = new Date();
        map.put("responseTime",now);

        namedParameterdbcTemplate.update(sql,map);

        return "回覆成功";
    }


    @Override
    public List<ReportVO> getReportByMember(Integer memberNo) {
        String sql = "SELECT (SELECT productName FROM product WHERE productNo = report.productNo) AS productName," +
                "(SELECT memberName FROM member_data WHERE memberNo = report.memberNo ) AS memberName ,reportContent," +
                "(SELECT reportNumber FROM product WHERE productNo = report.productNo) AS reportNumber," +
                "reportStat,reportTime,reportResponse,responseTime,reportImage,productNo,memberNo FROM report WHERE memberNo = :memberNo";

    Map<String,Object> map = new HashMap<>();
    map.put("memberNo",memberNo);

    List<ReportVO> list = namedParameterdbcTemplate.query(sql, map, new ReportRowMapper());

    if(list.size() > 0){
        return list;
    }else {
        return null;
    }


    }


}
