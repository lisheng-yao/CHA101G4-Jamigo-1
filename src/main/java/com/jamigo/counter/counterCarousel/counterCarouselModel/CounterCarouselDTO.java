package com.jamigo.counter.counterCarousel.counterCarouselModel;

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
@Table(name = "counter_carousel")
public class CounterCarouselDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counterCarouselNo;
	private Integer counterNo;
	private String counterCarouselText;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date counterCarouselStartTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date counterCarouselEndTime;
	
	public CounterCarouselDTO() {}

	public CounterCarouselDTO(Integer counterCarouselNo, Integer counterNo, String counterCarouselText,
			Date counterCarouselStartTime, Date counterCarouselEndTime) {
		super();
		this.counterCarouselNo = counterCarouselNo;
		this.counterNo = counterNo;
		this.counterCarouselText = counterCarouselText;
		this.counterCarouselStartTime = counterCarouselStartTime;
		this.counterCarouselEndTime = counterCarouselEndTime;
	}

	public Integer getCounterCarouselNo() {
		return counterCarouselNo;
	}

	public void setCounterCarouselNo(Integer counterCarouselNo) {
		this.counterCarouselNo = counterCarouselNo;
	}

	public Integer getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(Integer counterNo) {
		this.counterNo = counterNo;
	}

	public String getCounterCarouselText() {
		return counterCarouselText;
	}

	public void setCounterCarouselText(String counterCarouselText) {
		this.counterCarouselText = counterCarouselText;
	}

	public Date getCounterCarouselStartTime() {
		return counterCarouselStartTime;
	}

	public void setCounterCarouselStartTime(Date counterCarouselStartTime) {
		this.counterCarouselStartTime = counterCarouselStartTime;
	}

	public Date getCounterCarouselEndTime() {
		return counterCarouselEndTime;
	}

	public void setCounterCarouselEndTime(Date counterCarouselEndTime) {
		this.counterCarouselEndTime = counterCarouselEndTime;
	}
	
}
