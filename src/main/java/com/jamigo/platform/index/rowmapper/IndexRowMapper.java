package com.jamigo.platform.index.rowmapper;

import com.jamigo.platform.index.entity.IndexVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexRowMapper implements RowMapper {


    @Override
    public IndexVO mapRow(ResultSet rs, int rowNum) throws SQLException {

        IndexVO indexVO = new IndexVO();


        indexVO.setMainpageCarouselNo(rs.getInt("mainpageCarouselNo"));
        indexVO.setMainpageCarouselPic(rs.getBytes("mainpageCarouselPic"));
        indexVO.setMainpageCarouselStartTime(rs.getTimestamp("mainpageCarouselStartTime"));
        indexVO.setMainpageCarouselEndTime(rs.getTimestamp("mainpageCarouselEndTime"));



        return indexVO ;
    }
}
