package com.jamigo.counter.counterActivityPush.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "member_notice")
public class CounterActivityPushEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberNoticeNo;
	private Integer memberNo;
	private String noticeContent;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date noticeTime;
	private Byte readStat;
	
	public CounterActivityPushEntity() {}

	public CounterActivityPushEntity(Integer memberNoticeNo, Integer memberNo, String noticeContent, Date noticeTime,
			Byte readStat) {
		super();
		this.memberNoticeNo = memberNoticeNo;
		this.memberNo = memberNo;
		this.noticeContent = noticeContent;
		this.noticeTime = noticeTime;
		this.readStat = readStat;
	}

	public Integer getMemberNoticeNo() {
		return memberNoticeNo;
	}

	public void setMemberNoticeNo(Integer memberNoticeNo) {
		this.memberNoticeNo = memberNoticeNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public Byte getReadStat() {
		return readStat;
	}

	public void setReadStat(Byte readStat) {
		this.readStat = readStat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
