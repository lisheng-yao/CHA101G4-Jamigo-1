package com.jamigo.activity.activity_approve.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jamigo.activity.activity_approve.model.Activity;

//Repository 模塊，定義接口(增刪改查)
@Repository
public interface ActivityDAO extends JpaRepository<Activity, Integer> {

	// List<Activity> findByactivityDetail(String activityDetail);

	// 查找ActivityApprovalStat欄位值為指定值的所有Activity實體,並在ActivityService中調用方法
	List<Activity> findByActivityApprovalStat(Byte activityApprovalStat);

	List<Activity> findByCounterNo(Integer counterNo);

	static Optional<Activity> findById(String activityNo) {
		// TODO Auto-generated method stub
		return null;
	}

	//Activity findByActivityByCounterNo(Integer counterNo);
}
