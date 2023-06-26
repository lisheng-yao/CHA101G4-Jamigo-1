package com.jamigo.activity.activityCocoVer.activityEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "activity")
public class ActivityEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer activityNo;
	Integer counterNo;
	Integer activityPlaceNo;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	Date activityStartTime;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	Date activityEndTime;
	String activityName;
	String activityDetail;
	Integer activityLimit;
	Integer activityNumber;
	Integer activityCost;
	Integer adminNo;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Byte activityApprovalStat;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date activityRegStartTime;
	Date activityRegEndTime;
	Integer activityEvalTotalNumber;
	Integer activityEvalLevel;
	byte[] activityPic;
	Byte activityLev;
	
	public ActivityEntity() {}

	public ActivityEntity(Integer activityNo, Integer counterNo, Integer activityPlaceNo, Date activityStartTime,
			Date activityEndTime, String activityName, String activityDetail, Integer activityLimit,
			Integer activityNumber, Integer activityCost, Integer adminNo, Byte activityApprovalStat,
			Date activityRegStartTime, Date activityRegEndTime, Integer activityEvalTotalNumber,
			Integer activityEvalLevel, byte[] activityPic, Byte activityLev) {
		super();
		this.activityNo = activityNo;
		this.counterNo = counterNo;
		this.activityPlaceNo = activityPlaceNo;
		this.activityStartTime = activityStartTime;
		this.activityEndTime = activityEndTime;
		this.activityName = activityName;
		this.activityDetail = activityDetail;
		this.activityLimit = activityLimit;
		this.activityNumber = activityNumber;
		this.activityCost = activityCost;
		this.adminNo = adminNo;
		this.activityApprovalStat = activityApprovalStat;
		this.activityRegStartTime = activityRegStartTime;
		this.activityRegEndTime = activityRegEndTime;
		this.activityEvalTotalNumber = activityEvalTotalNumber;
		this.activityEvalLevel = activityEvalLevel;
		this.activityPic = activityPic;
		this.activityLev = activityLev;
	}

	public Integer getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(Integer activityNo) {
		this.activityNo = activityNo;
	}

	public Integer getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(Integer counterNo) {
		this.counterNo = counterNo;
	}

	public Integer getActivityPlaceNo() {
		return activityPlaceNo;
	}

	public void setActivityPlaceNo(Integer activityPlaceNo) {
		this.activityPlaceNo = activityPlaceNo;
	}

	public Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDetail() {
		return activityDetail;
	}

	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}

	public Integer getActivityLimit() {
		return activityLimit;
	}

	public void setActivityLimit(Integer activityLimit) {
		this.activityLimit = activityLimit;
	}

	public Integer getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(Integer activityNumber) {
		this.activityNumber = activityNumber;
	}

	public Integer getActivityCost() {
		return activityCost;
	}

	public void setActivityCost(Integer activityCost) {
		this.activityCost = activityCost;
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public Byte getActivityApprovalStat() {
		return activityApprovalStat;
	}

	public void setActivityApprovalStat(Byte activityApprovalStat) {
		this.activityApprovalStat = activityApprovalStat;
	}

	public Date getActivityRegStartTime() {
		return activityRegStartTime;
	}

	public void setActivityRegStartTime(Date activityRegStartTime) {
		this.activityRegStartTime = activityRegStartTime;
	}

	public Date getActivityRegEndTime() {
		return activityRegEndTime;
	}

	public void setActivityRegEndTime(Date activityRegEndTime) {
		this.activityRegEndTime = activityRegEndTime;
	}

	public Integer getActivityEvalTotalNumber() {
		return activityEvalTotalNumber;
	}

	public void setActivityEvalTotalNumber(Integer activityEvalTotalNumber) {
		this.activityEvalTotalNumber = activityEvalTotalNumber;
	}

	public Integer getActivityEvalLevel() {
		return activityEvalLevel;
	}

	public void setActivityEvalLevel(Integer activityEvalLevel) {
		this.activityEvalLevel = activityEvalLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public byte[] getActivityPic() {
		return activityPic;
	}

	public void setActivityPic(byte[] activityPic) {
		this.activityPic = activityPic;
	}

	public Byte getActivityLev() {
		return activityLev;
	}

	public void setActivityLev(Byte activityLev) {
		this.activityLev = activityLev;
	}
	
	
}
