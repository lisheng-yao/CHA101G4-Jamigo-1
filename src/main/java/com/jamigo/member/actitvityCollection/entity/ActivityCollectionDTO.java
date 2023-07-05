package com.jamigo.member.actitvityCollection.entity;

import java.io.Serializable;

import com.jamigo.activity.activity_approve.model.Activity;

public class ActivityCollectionDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	ActivityCollectionEntity activityCollectionEntity;

	private Activity activity;
	
	public ActivityCollectionDTO() {}

	public ActivityCollectionDTO(ActivityCollectionEntity activityCollectionEntity, Activity activity) {
		super();
		this.activityCollectionEntity = activityCollectionEntity;
		this.activity = activity;
	}

	public ActivityCollectionEntity getActivityCollectionEntity() {
		return activityCollectionEntity;
	}

	public void setActivityCollectionEntity(ActivityCollectionEntity activityCollectionEntity) {
		this.activityCollectionEntity = activityCollectionEntity;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
}
