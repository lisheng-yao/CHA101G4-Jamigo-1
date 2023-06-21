package com.jamigo.shop.shopCarousel.model;

import java.sql.Date;

public class ShopCarouselVO implements java.io.Serializable{
	private	Integer	shopCarouselNo;
	private	String shopCarouselTitle;
	private	String shopCarouselText;
	private byte[] shopCarouselPic;          
	private	Date shopCarouselStartTime;
	private	Date shopCarouselEndTime;
	private Integer shopCarouselState;
	private String shopCarouselUrl;
	
	public Integer getShopCarouselNo() {
		return shopCarouselNo;
	}
	public void setShopCarouselNo(Integer shopCarouselNo) {
		this.shopCarouselNo = shopCarouselNo;
	}
	public String getShopCarouselTitle() {
		return shopCarouselTitle;
	}
	public void setShopCarouselTitle(String shopCarouselTitle) {
		this.shopCarouselTitle = shopCarouselTitle;
	}
	public String getShopCarouselText() {
		return shopCarouselText;
	}
	public void setShopCarouselText(String shopCarouselText) {
		this.shopCarouselText = shopCarouselText;
	}
	public byte[] getShopCarouselPic() {
		return shopCarouselPic;
	}
	public void setShopCarouselPic(byte[] shopCarouselPic) {
		this.shopCarouselPic = shopCarouselPic;
	}
	public Date getShopCarouselStartTime() {
		return shopCarouselStartTime;
	}
	public void setShopCarouselStartTime(Date shopCarouselStartTime) {
		this.shopCarouselStartTime = shopCarouselStartTime;
	}
	public Date getShopCarouselEndTime() {
		return shopCarouselEndTime;
	}
	public void setShopCarouselEndTime(Date shopCarouselEndTime) {
		this.shopCarouselEndTime = shopCarouselEndTime;
	}

	public Integer getShopCarouselState() {
		return shopCarouselState;
	}

	public void setShopCarouselState(Integer shopCarouselState) {
		this.shopCarouselState = shopCarouselState;
	}

	public String getShopCarouselUrl() {
		return shopCarouselUrl;
	}

	public void setShopCarouselUrl(String shopCarouselUrl) {
		this.shopCarouselUrl = shopCarouselUrl;
	}	
	

}
