package com.jamigo.shop.wishlist.controller;


import com.jamigo.shop.wishlist.entity.ProductVO;
import com.jamigo.shop.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;


    @GetMapping("/wishlist/addone/{memberNo}/{productNo}")
    public String addOne(@PathVariable Integer memberNo,@PathVariable Integer productNo){

        return wishlistService.insertOne(memberNo,productNo);
    }

    @GetMapping("/wishlist/deleteone/{memberNo}/{productNo}")
    public String deleteOne(@PathVariable Integer memberNo,@PathVariable Integer productNo){

        return wishlistService.deleteOne(memberNo,productNo);
    }

    @GetMapping("/wishlist/getall/{memberNo}")
    public ResponseEntity<List<ProductVO>> getAllByMemberNo(@PathVariable Integer memberNo){

        List<ProductVO> list = wishlistService.getAllByMemberNo(memberNo);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/wishlist/checkWishedByMemberNo/{memberNo}")
    public ResponseEntity<List<Integer>> checkWishedByMemberNo(@PathVariable Integer memberNo){

        List<Integer> list = wishlistService.checkWishedByMemberNo(memberNo);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
