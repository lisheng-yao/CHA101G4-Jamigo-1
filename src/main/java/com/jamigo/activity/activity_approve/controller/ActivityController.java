package com.jamigo.activity.activity_approve.controller;

import java.io.IOException;
//import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamigo.activity.activity_approve.model.Activity;
import com.jamigo.activity.activity_approve.service.ActivityService;

@RestController
public class ActivityController {

	@Autowired // 注入實例
	private ActivityService activityService;

	// 新增線下活動申請表
	@PostMapping(value = "/backend/appform", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // 把input寫入的資料傳進去
	// 映射特定的HTTP請求方法到特定的處理函式
	// 創建映射POST請求到URI的路由並指定這個方法只接受multipart/form-data類型，因表單中有包含圖片上傳的輸入元素
	public Activity addActivity(@RequestParam("counterNo") Integer counterNo,

			@RequestParam("activityName") String activityName, @RequestParam("activityCost") Integer activityCost,
			@RequestParam("activityLimit") Integer activityLimit,
			@RequestParam("activityPlaceNo") Byte activityPlaceNo,
			@RequestParam("activityDetail") String activityDetail,
			@RequestParam("activityPic") MultipartFile activityPic,
			@RequestParam("activityRegStartTime") String activityRegStartTimeStr,
			@RequestParam("activityRegEndTime") String activityRegEndTimeStr,
			@RequestParam("activityStartTime") String activityStartTimeStr,
			@RequestParam("activityEndTime") String activityEndTimeStr, @RequestParam("activityLev") Byte activityLev) {
		// HTML表單不能直接傳遞LocalDateTime對象，所以在server端要將接收到的字串轉換為LocalDateTime
		LocalDateTime activityRegStartTime = LocalDateTime.parse(activityRegStartTimeStr);
		LocalDateTime activityRegEndTime = LocalDateTime.parse(activityRegEndTimeStr);
		LocalDateTime activityStartTime = LocalDateTime.parse(activityStartTimeStr);
		LocalDateTime activityEndTime = LocalDateTime.parse(activityEndTimeStr);
		// 創建一個Activity對象，將包含從表單中獲取的所有數據
		Activity activity = new Activity();
		// 運用setter方法填補從表單中獲取的數據
		activity.setCounterNo(counterNo);
		activity.setActivityName(activityName);
		activity.setActivityCost(activityCost);
		activity.setActivityLimit(activityLimit);
		activity.setActivityPlaceNo(activityPlaceNo);
		activity.setActivityDetail(activityDetail);
		activity.setActivityLev(activityLev);
		try {
			activity.setActivityPic(activityPic.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activity.setActivityRegStartTime(activityRegStartTime);
		activity.setActivityRegEndTime(activityRegEndTime);
		activity.setActivityStartTime(activityStartTime);
		activity.setActivityEndTime(activityEndTime);
		// 儲存 activity 並返回結果
		return activityService.save(activity);
	}

	// 列出活動申請表於審查頁面，並把資料添加到Model中
	@GetMapping("/backend/applist")
	public List<Activity> getAllActivities() {
		return activityService.getAllActivities();

	}

	// 依照活動編號映出相對應的活動詳情
	@GetMapping("/backend/appdetail/{activityNo}")
	public Activity getActDetail(@PathVariable("activityNo") Integer activityNo) {
		return activityService.getActDetail(activityNo);
	}

	// 列出申請單中審查通過的活動
	@GetMapping("/backend/actapprove")
	public List<Activity> getApprovedActivities() {
		return activityService.findByActApprStat((byte) 1);
	}

	// 依照點選之活動編號映出相對應的活動資訊
	@GetMapping("/backend/appinfo/{activityId}")
	public Activity getActInfo(@PathVariable("activityId") Integer activityNo) {// 獲取這個變量的值，並將其作為參數傳遞給你的控制器方法
		return activityService.getActDetail(activityNo);// 抓出sql相對的Id
	}

	// 更新審查狀態數據
	@PostMapping("/backend/appstatus")
	//ResponseEntity<?>代表整個HTTP響應：狀態碼、頭部資訊，以及體(body)。
	public ResponseEntity<?> updateActStatus(@RequestBody Activity activity) throws Exception {
		Activity updatedActivity = activityService.updateActStatus(activity);
		return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
	}
}