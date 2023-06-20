package com.jamigo.member.member_data.controller;

import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.member.member_data.dao.impl.MemberDataDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import static java.lang.System.out;


@RestController
public class DBGifReaderController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private MemberDataDaoImpl dao;


    @GetMapping("member/member_data/{memberNo}")
    public ResponseEntity<byte[]> findPic(@PathVariable("memberNo") Integer memberNo) {
        MemberData thismember = dao.selectById(memberNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        if (thismember != null) {
            byte[] thisPic = thismember.getMemberPic();
            out.println("把查到的物件的照片放上去");
            return new ResponseEntity<>(thisPic, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}






