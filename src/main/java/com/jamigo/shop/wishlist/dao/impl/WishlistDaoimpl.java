package com.jamigo.shop.wishlist.dao.impl;

import com.jamigo.shop.wishlist.dao.Wishlistdao;
import com.jamigo.shop.wishlist.entity.ProductVO;
import com.jamigo.shop.wishlist.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WishlistDaoimpl implements Wishlistdao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String insertOne(Integer memberNo, Integer productNo) {
        String sql = "INSERT INTO product_wishlist (memberNo,productNo) VALUES(:memberNo,:productNo)";


        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", memberNo);
        map.put("productNo", productNo);

        namedParameterJdbcTemplate.update(sql, map);
        return "加入成功";
    }

    @Override
    public List<ProductVO> getAllByMemberNo(Integer memberNo) {
        String sql = "SELECT product.productNo,counterNo,productName,productPrice,productInfo,evalTotalPeople,evalTotalScore," +
                "(SELECT counterName FROM counter WHERE counterNo = product.counterNo) AS counterName FROM product JOIN" +
                " product_wishlist ON product.productNo = product_wishlist.productNo WHERE product_wishlist.memberNo = :memberNo";

        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", memberNo);

        List<ProductVO> list = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return list;
    }

    @Override
    public String deleteOne(Integer memberNo, Integer productNo) {
        String sql = "DELETE FROM product_wishlist WHERE memberNo = :memberNo AND productNo = :productNo";

        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", memberNo);
        map.put("productNo", productNo);

        namedParameterJdbcTemplate.update(sql, map);

        return "刪除成功";
    }

    @Override
    public List<Integer> checkWishedByMemberNo(Integer memberNo) {
        String sql = "SELECT productNo FROM product_wishlist WHERE memberNo = :memberNo";

        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", memberNo);

        List<Integer> list = namedParameterJdbcTemplate.query(sql, map, (rs, rowNum) -> rs.getInt("productNo"));
        return list;
    }
}
