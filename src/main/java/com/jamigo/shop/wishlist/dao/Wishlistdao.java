package com.jamigo.shop.wishlist.dao;

import com.jamigo.shop.wishlist.entity.ProductVO;

import java.util.List;

public interface Wishlistdao {


    String insertOne(Integer memberNo,Integer productNo);

    List<ProductVO> getAllByMemberNo(Integer memberNo);

    String deleteOne(Integer memberNo,Integer productNo);
    List<Integer> checkWishedByMemberNo(Integer memberNo);
}
