package com.jamigo.activity.activity_approve.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamigo.activity.activity_approve.dao.ActivityDAO;
import com.jamigo.activity.activity_approve.model.Activity;

@Service
public class ActivityService {

	@Autowired
	private ActivityDAO activityDAO;

//存取申請表單
	public Activity save(Activity activity) {
		return activityDAO.save(activity);
	}

//列出申請表清單
	public List<Activity> getAllActivities() {
		// TODO Auto-generated method stub
		return activityDAO.findAll();
	}

	// 列出申請表單細項
	public Activity getActDetail(Integer activityNo) {
		// TODO Auto-generated method stub
		return activityDAO.findById(activityNo).orElse(null);
	}

//列出審查結果為通過的活動
	public List<Activity> findByActApprStat(Byte activityApprovalStat) {
		return activityDAO.findByActivityApprovalStat(activityApprovalStat);
	}

	// 列出前台所選活動項目的詳情
	public Activity getActInfo(Integer activityNo) {
		// TODO Auto-generated method stub
		return activityDAO.findById(activityNo).orElse(null);
	}

	// 更新審查狀態數據
	public Activity updateActStatus(Activity activity) throws Exception {
		// 查找到需要更新的活動
		Optional<Activity> existingActivity = activityDAO.findById(activity.getActivityNo());

		// 如果找到了活動，則更新它
		if (existingActivity.isPresent()) {
			Activity updateActStatus = existingActivity.get();
			updateActStatus.setActivityApprovalStat(activity.getActivityApprovalStat());
			return activityDAO.save(updateActStatus);
		} else {
			throw new Exception("活動未找到");
		}
	}

	// 透過櫃位Id查詢申請的活動狀態
	public List<Activity> getConResultById(Integer counterNo) {
		List<Activity> activities = activityDAO.findByCounterNo(counterNo);
		if (activities == null) {
			throw new EntityNotFoundException("not found activity" + counterNo);
		}
		return activities;
	}

	// 依照點選之活動編號映出相對應的活動資訊[櫃位後台]
	public Activity getCouInfo(Integer activityNo) {
		// TODO Auto-generated method stub
		return activityDAO.findById(activityNo).orElse(null);
	}


}