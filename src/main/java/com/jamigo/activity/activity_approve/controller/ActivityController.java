package com.jamigo.activity.activity_approve.controller;

import java.io.IOException;
//import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamigo.activity.activity_approve.dao.ActivityDAO;
import com.jamigo.activity.activity_approve.model.Activity;
import com.jamigo.activity.activity_approve.service.ActivityService;

@RestController
public class ActivityController {

	private ActivityService activityService;

	// 新增線下活動申請表
	@PostMapping(value = "/backend/appform", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // 把input寫入的資料傳進去
	// 映射特定的HTTP請求方法到特定的處理函式
	// 創建映射POST請求到URI的路由並指定這個方法只接受multipart/form-data類型，因表單中有包含圖片上傳的輸入元素
	public Activity addActivity(@RequestParam("counterNo") Integer counterNo,

			@RequestParam("activityName") String activityName, @RequestParam("activityCost") Integer activityCost,
			@RequestParam("activityLimit") Integer activityLimit, @RequestParam("activityPlaceNo") Byte activityPlaceNo,
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

	@GetMapping("/backend/appinfo/{activityId}")
	// 依照點選之活動編號映出相對應的活動資訊
	public Activity getActInfo(@PathVariable("activityId") Integer activityNo) {
		return activityService.getActDetail(activityNo);// 抓出sql相對之Id
	}

	@PostMapping("/backend/appstatus")
	// 更新審查狀態數據
	// 代表整個HTTP響應
	public ResponseEntity<?> updateActStatus(@RequestBody Activity activity) throws Exception {
		Activity updatedActivity = activityService.updateActStatus(activity);
		return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
	}

	// 依照活動編號映出相對應的活動資料[櫃位後台]
	@GetMapping("/backend/couresult/{counterNo}")
	public List<Activity> getConResultById(@PathVariable("counterNo") Integer counterNo, HttpSession session) {
		session.setAttribute("counterSession", counterNo); // store the counterNo into the session
		return activityService.getConResultById(counterNo);
	}

	// 依照點選之活動編號映出相對應的活動資訊[櫃位後台]
	@GetMapping("/backend/couninfo/{activityNo}")
	public Activity getCouInfo(@PathVariable("activityNo") Integer activityNo) {
		return activityService.getActDetail(activityNo);// 抓出sql相對之Id
	}

	@Autowired
	public ActivityController(ActivityService activityService) {
		this.activityService = activityService;
	}

//修改活動申請單[櫃位後台]
	@PutMapping("/backend/applyupdate/{activityNo}")
	public ResponseEntity<Activity> updateActivity(@PathVariable Integer activityNo,
			@RequestBody Activity updatedActivity) {
		Activity activity = activityService.getActUpdate(activityNo);
		// 用 updatedActivity 的資訊來更新找到的 Activity
		activity.setActivityName(updatedActivity.getActivityName());
		activity.setActivityCost(updatedActivity.getActivityCost());
		activity.setActivityLimit(updatedActivity.getActivityLimit());
		activity.setActivityDetail(updatedActivity.getActivityDetail());
		activity.setActivityRegStartTime(updatedActivity.getActivityRegStartTime());
		activity.setActivityRegEndTime(updatedActivity.getActivityRegEndTime());
		activity.setActivityStartTime(updatedActivity.getActivityStartTime());
		activity.setActivityEndTime(updatedActivity.getActivityEndTime());
		activity.setActivityPic(updatedActivity.getActivityPic());

		// 把更新過的 Activity 儲存回資料庫
		Activity savedActivity = activityService.saveActivity(activity);
		return new ResponseEntity<>(savedActivity, HttpStatus.OK);
	}
	
	//上傳修改圖片與存放
////	 @PostMapping("/Jamigo/backend/couninfo/{activityNo}/uploadImage")
////	    public ResponseEntity<String> uploadImage(@PathVariable String activityNo, @RequestBody byte[] imageBytes) {
////	        Optional<Activity> optionalActivity = activityService.findById(activityNo);
////	        if (optionalActivity.isPresent()) {
////	            Activity activity = optionalActivity.get();
////	            activity.setActivityPic(imageBytes);
////	            activityService.save(activity);
////	            return ResponseEntity.ok().body("Image upload success");
////	        } else {
////	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activity not found");
////	        }
//	    }
}
