package com.jamigo.member.member_data.controller;

import com.jamigo.member.member_data.Service.impl.MemberServiceImpl;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.member.member_data.dao.impl.MemberDataDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


    @WebServlet("/DBGifReaderController")
public class DBGifReaderController extends HttpServlet {
        private static final long serialVersionUID = 1L;
        @Autowired
        private MemberDataDaoImpl dao;

        public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

            res.setContentType("image/gif");
            ServletOutputStream out = res.getOutputStream();

            try {
                Integer memberNoA = Integer.valueOf(req.getParameter("memberNo"));
                MemberData thismemberNo = dao.selectById(memberNoA);
                byte[] thisPic = thismemberNo.getMemberPic();

                if (thisPic != null) {
                    out.write(thisPic);
                    System.out.println("把查到的物件的照片放上去" );
                } else {
                    out.write(getImageBytes("/WEB-INF/member/image/gray.jpg"));
                    System.out.println("把預設的照片放上去" );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private byte[] getImageBytes(String imageUrl) throws IOException {
            InputStream in = getServletContext().getResourceAsStream(imageUrl);
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
