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

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
public class DBGifReaderController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private MemberDataDaoImpl dao;
    @Autowired
    private HttpServletRequest request;


    @GetMapping("member/member_data/{memberNo}")
    public ResponseEntity<byte[]> findPic(@PathVariable("memberNo") Integer memberNo) {
        MemberData thismember = dao.selectById(memberNo);
        if (thismember != null) {
            byte[] thisPic = thismember.getMemberPic();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            System.out.println("把查到的物件的照片放上去");
            return new ResponseEntity<>(thisPic, headers, HttpStatus.OK);
        } else {
            System.out.println("把預設的照片放上去");
            try {
                byte[] gray= getImageBytes("/static/member/member/image/gray.jpg");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(gray, headers, HttpStatus.OK);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private byte[] getImageBytes(String imageUrl) throws IOException {
        InputStream in = request.getServletContext().getResourceAsStream(imageUrl);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = in.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}






