package com.jamigo.platform.index.dao.impl;


import com.jamigo.platform.index.dao.IndexDao;
import com.jamigo.platform.index.entity.IndexVO;
import com.jamigo.platform.index.rowmapper.IndexRowMapper;
import com.jamigo.platform.index.rowmapper.ProductRowMapper;
import com.jamigo.shop.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IndexDaoimpl implements IndexDao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterdbcTemplate;


    @Override
    public String insertOne(IndexVO indexVO) {
        String sql = "INSERT INTO mainpage_carousel(mainpageCarouselPic,mainpageCarouselStartTime,mainpageCarouselEndTime) VALUES(:mainpageCarouselPic,:mainpageCarouselStartTime,:mainpageCarouselEndTime)";


        Map<String, Object> map = new HashMap<>();
        map.put("mainpageCarouselPic", indexVO.getMainpageCarouselPic());

        map.put("mainpageCarouselStartTime", indexVO.getMainpageCarouselStartTime());
        map.put("mainpageCarouselEndTime", indexVO.getMainpageCarouselEndTime());

        namedParameterdbcTemplate.update(sql, new MapSqlParameterSource(map));


        return "新增成功";
    }

    @Override
    public List<IndexVO> getAll() {
        String sql = "SELECT mainpageCarouselNo,mainpageCarouselPic,mainpageCarouselStartTime,mainpageCarouselEndTime FROM mainpage_carousel";

        Map<String, Object> map = new HashMap<>();

        List<IndexVO> list = namedParameterdbcTemplate.query(sql, map, new IndexRowMapper());

        return list;
    }

    @Override
    public String deleteOne(Integer mainpageCarouselNo) {
        String sql = "DELETE FROM mainpage_carousel WHERE mainpageCarouselNo=:mainpageCarouselNo";

        Map<String, Object> map = new HashMap<>();

        map.put("mainpageCarouselNo", mainpageCarouselNo);

        namedParameterdbcTemplate.update(sql, map);

        return "刪除成功";
    }

    @Override
    public List<Product> getpopularproduct() {
        String sql = "SELECT p.productNo, p.counterNo, c.counterName, p.productName, p.productPrice, p.productInfo, p.productSaleNum, p.evalTotalPeople, p.evalTotalScore " +
                "FROM product p " +
                "INNER JOIN counter c ON p.counterNo = c.counterNo " +
                "WHERE p.productStat = 1 " +
                "ORDER BY p.productSaleNum DESC " +
                "LIMIT :count";

        int count = 10;
        Map<String, Object> map = new HashMap();
        map.put("count", Math.min(count, 10));

        List<Product> list = namedParameterdbcTemplate.query(sql, map, new ProductRowMapper());
        return list;
    }
}
