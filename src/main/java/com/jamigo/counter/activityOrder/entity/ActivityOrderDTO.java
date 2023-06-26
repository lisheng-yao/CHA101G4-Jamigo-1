package com.jamigo.counter.activityOrder.entity;

import java.util.List;

import com.jamigo.activity.attendee.entity.ActivityAttendeeVO;

public class ActivityOrderDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	ActivityOrderVO activityOrderVO;
	List<ActivityAttendeeVO> activityAttendeeVOList;
	
	public ActivityOrderDTO() {}

	public ActivityOrderDTO(ActivityOrderVO activityOrderVO, List<ActivityAttendeeVO> activityAttendeeVOList) {
		super();
		this.activityOrderVO = activityOrderVO;
		this.activityAttendeeVOList = activityAttendeeVOList;
	}



	public ActivityOrderVO getActivityOrderVO() {
		return activityOrderVO;
	}

	public void setActivityOrderVO(ActivityOrderVO activityOrderVO) {
		this.activityOrderVO = activityOrderVO;
	}

	public List<ActivityAttendeeVO> getActivityAttendeeVOList() {
		return activityAttendeeVOList;
	}

	public void setActivityAttendeeVOList(List<ActivityAttendeeVO> activityAttendeeVOList) {
		this.activityAttendeeVOList = activityAttendeeVOList;
	}

	
	
}
