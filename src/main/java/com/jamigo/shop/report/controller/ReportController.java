package com.jamigo.shop.report.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jamigo.shop.report.dto.ReportRequest;
import com.jamigo.shop.report.entity.ReportVO;
import com.jamigo.shop.report.service.ReportService;


@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/insertOne")
    public ResponseEntity<Map<String, String>> insertOne(@RequestBody ReportRequest reportRequest) {

        //錯誤判斷
        Integer productNo = reportRequest.getProductNo();
        Integer memberNo = reportRequest.getMemberNo();
        String reportContent = Arrays.toString(reportRequest.getReportContents());
        reportRequest.setReportContent(reportContent);

        Map<String, String> map = new HashMap<>();

        if (productNo == null || productNo == 0) {
            map.put("productNo", "請輸入產品編號");
        }
        if (memberNo == null || memberNo == 0) {
            map.put("memberNo", "請輸入會員編號");
        }
        if (reportContent.equals("[]")) {
            map.put("reportContent", "您尚未發表意見~");
        }
        if (!map.isEmpty()) {
            System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }

        //儲存資料
        map.put("message", reportService.insertOne(reportRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<ReportVO>> getAllReports() {

        List<ReportVO> list = reportService.getAllReports();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/updateOne")
    public ResponseEntity<?> updateOne(@RequestBody ReportRequest reportRequest) {
        System.out.println("進來ㄌ");
        //錯誤判斷 陣列轉字串 回應內容
        String reportresponse = Arrays.toString(reportRequest.getReportResponses());
        Map<String, String> map = new HashMap<>();

        if (reportresponse.equals("[]") || reportresponse.trim().isEmpty()) {
            map.put("reportContent", "您尚未回覆~");
        }
        if (!map.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }

        reportRequest.setReportResponse(reportresponse);
        map.put("message", reportService.updateResponse(reportRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @GetMapping("/report/{memberNo}/{productNo}")
    public ResponseEntity<ReportVO> getReportOne(@PathVariable Integer memberNo, @PathVariable Integer productNo) {
        System.out.println("近來");
        List<ReportVO> list = reportService.getReportByPrimaryKey(memberNo, productNo);

        if (list != null) {
            return ResponseEntity.ok(list.get(0));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/memberreports/{memberNo}")
    public ResponseEntity<?> getReportByMember(@PathVariable Integer memberNo) {

        List<ReportVO> list = reportService.getReportByMember(memberNo);


        if (list == null) {
            Map<String,String> map = new HashMap<>();
            map.put("message","您真棒!在本百貨您尚無檢舉紀錄");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } else
            return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
