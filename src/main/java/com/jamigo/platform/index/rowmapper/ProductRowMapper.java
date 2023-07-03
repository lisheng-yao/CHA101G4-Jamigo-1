package com.jamigo.platform.index.rowmapper;

import com.jamigo.platform.index.entity.ProductVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductVO product = new ProductVO();

        product.setProductNo(rs.getInt("productNo"));
        product.setCounterNo(rs.getInt("counterNo"));
        product.setCounterName(rs.getString("counterName"));
        product.setProductName(rs.getString("productName"));
        product.setProductPrice(rs.getInt("productPrice"));
        product.setProductInfo(rs.getString("productInfo"));
        product.setProductSaleNum(rs.getInt("productSaleNum"));
        product.setEvalTotalPeople(rs.getInt("evalTotalPeople"));
        product.setEvalTotalScore(rs.getInt("evalTotalScore"));

        return product;
    }
}
