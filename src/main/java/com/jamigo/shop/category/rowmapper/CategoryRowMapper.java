package com.jamigo.shop.category.rowmapper;

import com.jamigo.shop.category.entity.CategoryVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<CategoryVO> {

    public CategoryVO mapRow(ResultSet rs,int rowNum) throws SQLException{

        CategoryVO categoryVO = new CategoryVO();

        categoryVO.setProductCatNo(rs.getInt("productCatNo"));
        categoryVO.setProductCatName(rs.getString("productCatName"));
        categoryVO.setProductCatPerson(rs.getString("productCatPerson"));

        int productCount = rs.getInt("productCount");
        categoryVO.setProductCount(productCount);

        return categoryVO;
    }

}
