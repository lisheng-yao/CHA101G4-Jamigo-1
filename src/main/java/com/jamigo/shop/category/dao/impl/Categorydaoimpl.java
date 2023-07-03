package com.jamigo.shop.category.dao.impl;


import com.jamigo.shop.category.dao.Categorydao;
import com.jamigo.shop.category.entity.CategoryVO;
import com.jamigo.shop.category.rowmapper.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Categorydaoimpl implements Categorydao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<CategoryVO> getAll() {
        String sql = "SELECT pc.productCatPerson, pc.productCatNo, pc.productCatName, counts.productCount " +
                "FROM product_category pc " +
                "LEFT JOIN (SELECT productCatNo, COUNT(*) AS productCount FROM product GROUP BY productCatNo) AS counts " +
                "ON pc.productCatNo = counts.productCatNo";


        Map<String, Object> map = new HashMap<>();

        List<CategoryVO> list = namedParameterJdbcTemplate.query(sql, map, new CategoryRowMapper());

        return list;
    }

    @Override
    public String addOne(CategoryVO categoryVO) {
        String sql = "INSERT INTO product_category(productCatName,productCatPerson) VALUES(:productCatName,:productCatPerson)";

        Map<String, Object> map = new HashMap<>();

        map.put("productCatName", categoryVO.getProductCatName());
        map.put("productCatPerson", categoryVO.getProductCatPerson());
        namedParameterJdbcTemplate.update(sql, map);

        return "新增成功";
    }

    @Override
    public String delete(Integer productCatNo) {
        String sql = "DELETE FROM product_category WHERE productCatNo=:productCatNo";

        Map<String, Object> map = new HashMap<>();
        map.put("productCatNo", productCatNo);

        namedParameterJdbcTemplate.update(sql, map);

        return "刪除成功";
    }
}
