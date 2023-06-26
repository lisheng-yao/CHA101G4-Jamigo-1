package com.jamigo.platform.index.dao.impl;


import com.jamigo.platform.index.dao.IndexDao;
import com.jamigo.platform.index.entity.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
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

        namedParameterdbcTemplate.update(sql,new MapSqlParameterSource(map));


        return "新增成功";
    }
}
