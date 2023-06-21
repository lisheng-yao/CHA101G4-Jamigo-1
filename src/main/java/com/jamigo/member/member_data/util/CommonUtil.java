package com.jamigo.member.member_data.util;


import com.jamigo.member.member_data.entity.MemberData;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.security.SecureRandom;

import static com.jamigo.member.member_data.util.Constants.GSON;
import static com.jamigo.member.member_data.util.Constants.JSON_MIME_TYPE;

public class CommonUtil {

    public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {

        try (BufferedReader br = request.getReader()) {
            return GSON.fromJson(br, classOfPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
        response.setContentType(JSON_MIME_TYPE);
        try (PrintWriter pw = response.getWriter()) {
            pw.print(GSON.toJson(pojo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }

