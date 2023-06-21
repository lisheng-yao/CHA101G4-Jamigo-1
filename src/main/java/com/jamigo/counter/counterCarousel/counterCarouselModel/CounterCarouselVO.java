package com.jamigo.counter.counterCarousel.counterCarouselModel;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mainpage_carousel")
public class CounterCarouselVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Byte mainpageCarouselNo;
	private byte[] mainpageCarouselPic;
	private Date mainpageCarouselStartTime;
	private Date mainpageCarouselEndTime;
	
	public CounterCarouselVO() {}

	public CounterCarouselVO(Byte mainpageCarouselNo, byte[] mainpageCarouselPic, Date mainpageCarouselStartTime,
			Date mainpageCarouselEndTime) {
		super();
		this.mainpageCarouselNo = mainpageCarouselNo;
		this.mainpageCarouselPic = mainpageCarouselPic;
		this.mainpageCarouselStartTime = mainpageCarouselStartTime;
		this.mainpageCarouselEndTime = mainpageCarouselEndTime;
	}

	public Byte getMainpageCarouselNo() {
		return mainpageCarouselNo;
	}

	public void setMainpageCarouselNo(Byte mainpageCarouselNo) {
		this.mainpageCarouselNo = mainpageCarouselNo;
	}

	public byte[] getMainpageCarouselPic() {
		return mainpageCarouselPic;
	}

	public void setMainpageCarouselPic(byte[] mainpageCarouselPic) {
		this.mainpageCarouselPic = mainpageCarouselPic;
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
