package com.jamigo.activity.attendee.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attendee")
public class ActivityAttendeeVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendeeNo;
	private Integer activityOrderNo;
	private String attendeeName;
	private Byte attendeeGender;
	private Byte attendeeAge;
	
	public ActivityAttendeeVO() {}

	public ActivityAttendeeVO(Integer attendeeNo, Integer activityOrderNo, String attendeeName, Byte attendeeGender,
			Byte attendeeAge) {
		super();
		this.attendeeNo = attendeeNo;
		this.activityOrderNo = activityOrderNo;
		this.attendeeName = attendeeName;
		this.attendeeGender = attendeeGender;
		this.attendeeAge = attendeeAge;
	}

	public Integer getAttendeeNo() {
		return attendeeNo;
	}

	public void setAttendeeNo(Integer attendeeNo) {
		this.attendeeNo = attendeeNo;
	}

	public Integer getActivityOrderNo() {
		return activityOrderNo;
	}

	public void setActivityOrderNo(Integer activityOrderNo) {
		this.activityOrderNo = activityOrderNo;
	}

	public String getAttendeeName() {
		return attendeeName;
	}

	public void setAttendeeName(String attendeeName) {
		this.attendeeName = attendeeName;
	}

	public Byte getAttendeeGender() {
		return attendeeGender;
	}

	public void setAttendeeGender(Byte attendeeGender) {
		this.attendeeGender = attendeeGender;
	}

	public Byte getAttendeeAge() {
		return attendeeAge;
	}

	public void setAttendeeAge(Byte attendeeAge) {
		this.attendeeAge = attendeeAge;
	}
	
	
}
