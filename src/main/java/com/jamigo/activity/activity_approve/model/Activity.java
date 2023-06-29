package com.jamigo.activity.activity_approve.model;

import java.sql.Blob;
//import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//實體建
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Table(name="activity",schema ="jamigo")
public class Activity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "activityNo")
	    private Integer activityNo;

	    @Column(name = "counterNo")
	    //@OneToOne
	    //@JoinColumn(name = "counterNo")
	    private Integer counterNo;

	    @Column(name = "activityPlaceNo", nullable = false)
	    private Byte activityPlaceNo;

	    @Column(name = "activityStartTime", nullable = false)
	    private LocalDateTime activityStartTime;

	    @Column(name = "activityEndTime", nullable = false)
	    private LocalDateTime activityEndTime;

	    @Column(name = "activityName", length = 100, nullable = false)
	    private String activityName;

	    @Column(name = "activityDetail", length = 1000, nullable = false)
	    private String activityDetail;

	    @Column(name = "activityLimit", nullable = false)
	    private Integer activityLimit;

	    @Column(name = "activityNumber")
	    private Integer activityNumber;

	    @Column(name = "activityCost", nullable = false)
	    private Integer activityCost;

	    @Column(name = "adminNo")
	    private Integer adminNo;

	    @Column(name = "activityApprovalStat")
	    private Byte activityApprovalStat;

	    @Column(name = "activityRegStartTime", nullable = false)
	    private LocalDateTime activityRegStartTime;

	    @Column(name = "activityRegEndTime", nullable = false)
	    private LocalDateTime activityRegEndTime;

	    @Column(name = "activityEvalTotalNumber")
	    private Integer activityEvalTotalNumber;

	    @Column(name = "activityEvalLevel")
	    private Integer activityEvalLevel;

	    @Column(name = "activityPic")
	    private byte[] activityPic;
	    
	    @Column(name = "activityLev")
	    private Byte activityLev;

}