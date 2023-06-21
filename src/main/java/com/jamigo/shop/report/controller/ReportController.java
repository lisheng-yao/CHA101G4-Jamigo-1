<<<<<<< HEAD
//package com.jamigo.shop.report.controller;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jamigo.shop.report.dto.ReportRequest;
//import com.jamigo.shop.report.entity.ReportVO;
//import com.jamigo.shop.report.service.ReportService;
//
//@RestController
//public class ReportController {
//
//	@Autowired
//	private ReportService reportService;
//
//	@PostMapping("/insertOne")
//	public ResponseEntity<?> insertOne(@RequestBody ReportRequest reportRequest) {
//		System.out.println("進入!");
//
//		//錯誤判斷
//		Integer productNo = reportRequest.getProductNo();
//		Integer memberNo = reportRequest.getMemberNo();
//		String reportContent = Arrays.toString(reportRequest.getReportContents());
//		reportRequest.setReportContent(reportContent);
//
//	    Map<String, String> map = new HashMap<>();
//
//		if(productNo == null || productNo == 0) {
//			map.put("productNo","請輸入產品編號");
//		}
//		if(memberNo == null || memberNo == 0) {
//			map.put("memberNo","請輸入會員編號");
//		}
//		if (reportContent.equals("[]") || reportContent.trim().isEmpty()) {
//			map.put("reportContent","您尚未發表意見~");
//		}
//		if (!map.isEmpty()) {
//			System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map));
//
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
//		}
//
//		//儲存資料
//		map.put("message",reportService.insertOne(reportRequest));
//
//			return ResponseEntity.status(HttpStatus.CREATED).body(map) ;
//	}
//
//	@PostMapping("/updateOne")
//	public ResponseEntity<?> updateOne(@RequestBody ReportRequest reportRequest){
//
//		//錯誤判斷 陣列轉字串 回應內容
//		String reportresponse = Arrays.toString(reportRequest.getReportResponses());
//		reportRequest.setReportResponse(reportresponse);
//
//		Map<String, String> map = new HashMap<>();
//
//		if (reportresponse.equals("[]") || reportresponse.trim().isEmpty()) {
//			map.put("reportContent","您尚未發表意見~");
//		}
//		if (!map.isEmpty()) {
//			System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map));
//
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
//		}
//
////		update
//
//
//
//
//		return ResponseEntity.status(HttpStatus.CREATED).BODY(map);
//	}
//
//	@GetMapping("/reports/{memberNo}/{productNo}")
//	public ResponseEntity<ReportVO> getReportOne(@PathVariable Integer memberNo , Integer productNo) {
//		ReportVO reportVO = reportService.getReportByPrimaryKey(memberNo ,productNo);
//
//		//未完
//		if(reportVO != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(reportVO);
//		}else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
//		}
//	}
//
//	@PostMapping("/AllReports")
//	public List<ReportVO> getAll(){
//
//		List<ReportVO> list = new ArrayList<>();
//
//		//檢舉狀態、商品名稱、檢舉內容、會員名稱、(依商品編號查詢出現次數)、檢舉時間////客服回應、回應時間
//		//取得資料庫資料
//		list = reportService.getAllReports();
//
//		return list;
//	}
//
//
//}
=======
package com.jamigo.shop.report.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		
		if(productNo == null || productNo == 0) {
			map.put("productNo","請輸入產品編號");
		}
		if(memberNo == null || memberNo == 0) {
			map.put("memberNo","請輸入會員編號");
		}
		if (reportContent.equals("[]")) {
			map.put("reportContent","您尚未發表意見~");
		}
		if (!map.isEmpty()) {
			System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
	  
		//儲存資料
		map.put("message",reportService.insertOne(reportRequest));
		
			return ResponseEntity.status(HttpStatus.CREATED).body(map) ;
	}

	@GetMapping("/reports")
	public ResponseEntity<List<ReportVO>> getAllReports(){
		System.out.println("進入!");

		List<ReportVO> list = reportService.getAllReports();

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@PostMapping("/updateOne")
	public ResponseEntity<?> updateOne(@RequestBody ReportRequest reportRequest){

		//錯誤判斷 陣列轉字串 回應內容
		String reportresponse = Arrays.toString(reportRequest.getReportResponses());
		reportRequest.setReportResponse(reportresponse);

		Map<String, String> map = new HashMap<>();

		if (reportresponse.equals("[]") || reportresponse.trim().isEmpty()) {
			map.put("reportContent","您尚未發表意見~");
		}
		if (!map.isEmpty()) {
			System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map));

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}

		//update


		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}

	@GetMapping("/reports/{memberNo}/{productNo}")
	public ResponseEntity<ReportVO> getReportOne(@PathVariable Integer memberNo , Integer productNo) {
		ReportVO reportVO = reportService.getReportByPrimaryKey(memberNo ,productNo);

		//未完
		if(reportVO != null) {
			return ResponseEntity.status(HttpStatus.OK).body(reportVO);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
		}
	} 
	

	
	
}
>>>>>>> Shawn
