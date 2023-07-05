package com.jamigo.member.actitvityCollection.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jamigo.activity.activity_approve.model.Activity;

@Entity
@Table(name = "activity_collection")
@IdClass(ActivityCollectionEntity.class)
public class ActivityCollectionEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer activityNo;

	@Id
	private Integer memberNo;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "activityNo",
//				insertable = false, updatable = false, nullable = true)
//	private Activity activity;
	
	public ActivityCollectionEntity() {}

	
//	public ActivityCollectionEntity(Integer activityNo, Integer memberNo, Activity activity) {
//		this.activityNo = activityNo;
//		this.memberNo = memberNo;
//		this.activity = activity;
//	}

	public ActivityCollectionEntity(Integer activityNo, Integer memberNo) {
		super();
		this.activityNo = activityNo;
		this.memberNo = memberNo;
	}


	public Integer getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(Integer activityNo) {
		this.activityNo = activityNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

//	public Activity getActivity() {
//		return activity;
//	}
//
//	public void setActivity(Activity activity) {
//		this.activity = activity;
//	}
	
}
