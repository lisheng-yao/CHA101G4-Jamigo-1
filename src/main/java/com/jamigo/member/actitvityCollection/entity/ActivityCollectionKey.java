package com.jamigo.member.actitvityCollection.entity;

import java.io.Serializable;
import java.util.Objects;

public class ActivityCollectionKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer activityNo;
	private Integer memberNo;
	
	public ActivityCollectionKey() {}

	public ActivityCollectionKey(Integer activityNo, Integer memberNo) {
		this.activityNo = activityNo;
		this.memberNo = memberNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activityNo, memberNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityCollectionKey other = (ActivityCollectionKey) obj;
		return Objects.equals(activityNo, other.activityNo) && Objects.equals(memberNo, other.memberNo);
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
