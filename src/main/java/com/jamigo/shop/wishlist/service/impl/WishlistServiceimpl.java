package com.jamigo.shop.wishlist.service.impl;

import com.jamigo.shop.wishlist.dao.Wishlistdao;
import com.jamigo.shop.wishlist.entity.ProductVO;
import com.jamigo.shop.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WishlistServiceimpl implements WishlistService {

    @Autowired
    private Wishlistdao wishlistdao;

    @Override
    public String insertOne(Integer memberNo, Integer productNo) {
return wishlistdao.insertOne(memberNo,productNo);
    }

    @Override
    public List<ProductVO> getAllByMemberNo(Integer memberNo) {
        return wishlistdao.getAllByMemberNo(memberNo);
    }

    @Override
    public String deleteOne(Integer memberNo, Integer productNo) {
        return wishlistdao.deleteOne(memberNo,productNo);
    }

    @Override
    public List<Integer> checkWishedByMemberNo(Integer memberNo) {
        return wishlistdao.checkWishedByMemberNo(memberNo);
    }
}
