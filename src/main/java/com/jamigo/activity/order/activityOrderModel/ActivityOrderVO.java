package com.jamigo.activity.order.activityOrderModel;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import com.jamigo.activity.attendee.activityAttendeeModel.ActivityAttendeeVO;
import com.jamigo.member.member_data.entity.MemberData;

@Entity
@DynamicUpdate
@Table(name = "activity_order")
public class ActivityOrderVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityOrderNo;
	private Integer activityNo;
	private Integer memberNo;
	@CreatedDate
	private Date activityEnrollmentTime;
	private Byte activityPaymentStat;
	private Integer memberCouponNo;
	private Integer numberOfAttendee;
	private String commentDetail;
	private Integer activityScore;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberNo",
				insertable = false, updatable = false)
	private MemberData memberData;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "activityOrderNo",
				referencedColumnName = "activityOrderNo")
	private List<ActivityAttendeeVO> activityAttendeeVO;
	
	public ActivityOrderVO() {}


	public Integer getActivityOrderNo() {
		return activityOrderNo;
	}


	public void setActivityOrderNo(Integer activityOrderNo) {
		this.activityOrderNo = activityOrderNo;
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


	public Date getActivityEnrollmentTime() {
		return activityEnrollmentTime;
	}


	public void setActivityEnrollmentTime(Date activityEnrollmentTime) {
		this.activityEnrollmentTime = activityEnrollmentTime;
	}


	public Byte getActivityPaymentStat() {
		return activityPaymentStat;
	}


	public void setActivityPaymentStat(Byte activityPaymentStat) {
		this.activityPaymentStat = activityPaymentStat;
	}


	public Integer getMemberCouponNo() {
		return memberCouponNo;
	}


	public void setMemberCouponNo(Integer memberCouponNo) {
		this.memberCouponNo = memberCouponNo;
	}


	public Integer getNumberOfAttendee() {
		return numberOfAttendee;
	}


	public void setNumberOfAttendee(Integer numberOfAttendee) {
		this.numberOfAttendee = numberOfAttendee;
	}


	public String getCommentDetail() {
		return commentDetail;
	}


	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}


	public Integer getActivityScore() {
		return activityScore;
	}


	public void setActivityScore(Integer activityScore) {
		this.activityScore = activityScore;
	}


	public MemberData getMemberData() {
		return memberData;
	}


	public void setMemberData(MemberData memberData) {
		this.memberData = memberData;
	}


	public List<ActivityAttendeeVO> getActivityAttendeeVO() {
		return activityAttendeeVO;
	}


	public void setActivityAttendeeVO(List<ActivityAttendeeVO> activityAttendeeVO) {
		this.activityAttendeeVO = activityAttendeeVO;
	}

	
	
}
