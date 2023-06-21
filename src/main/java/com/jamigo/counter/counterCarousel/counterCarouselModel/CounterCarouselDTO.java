package com.jamigo.counter.counterCarousel.counterCarouselModel;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mainpage_carousel")
public class CounterCarouselDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Byte mainpageCarouselNo;
	private Date mainpageCarouselStartTime;
	private Date mainpageCarouselEndTime;
	
	public CounterCarouselDTO() {}

	public CounterCarouselDTO(Byte mainpageCarouselNo, Date mainpageCarouselStartTime, Date mainpageCarouselEndTime) {
		super();
		this.mainpageCarouselNo = mainpageCarouselNo;
		this.mainpageCarouselStartTime = mainpageCarouselStartTime;
		this.mainpageCarouselEndTime = mainpageCarouselEndTime;
	}

	public Byte getMainpageCarouselNo() {
		return mainpageCarouselNo;
	}

	public void setMainpageCarouselNo(Byte mainpageCarouselNo) {
		this.mainpageCarouselNo = mainpageCarouselNo;
	}

	public Date getMainpageCarouselStartTime() {
		return mainpageCarouselStartTime;
	}

	public void setMainpageCarouselStartTime(Date mainpageCarouselStartTime) {
		this.mainpageCarouselStartTime = mainpageCarouselStartTime;
	}

	public Date getMainpageCarouselEndTime() {
		return mainpageCarouselEndTime;
	}

	public void setMainpageCarouselEndTime(Date mainpageCarouselEndTime) {
		this.mainpageCarouselEndTime = mainpageCarouselEndTime;
	}
	
}
