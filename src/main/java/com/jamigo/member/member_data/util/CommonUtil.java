package com.jamigo.member.member_data.util;

//import static core.util.Constants.DATASOURCE;

//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import javax.json.JsonWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

import static com.jamigo.member.member_data.util.Constants.GSON;
import static com.jamigo.member.member_data.util.Constants.JSON_MIME_TYPE;

public class CommonUtil {

    //	public static Connection getConnection() throws NamingException, SQLException {
//		if (DATASOURCE == null) {
//			DATASOURCE = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/javaFramework");
//		}
//		return DATASOURCE.getConnection();
//	}

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

//    public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {
//        try (JsonReader reader = Json.createReader(request.getReader())) {
//            JsonObject jsonObject = reader.readObject();
//            // 将JsonObject转换为POJO对象
//            P pojo = memberdata// 执行将JsonObject转换为POJO的逻辑
//            return pojo;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
//        response.setContentType("application/json");
//        try (PrintWriter pw = response.getWriter()) {
//            JsonWriter writer = Json.createWriter(pw);
//            JsonObject jsonObject = // 将POJO对象转换为JsonObject的逻辑
//                    writer.writeObject(jsonObject);
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

