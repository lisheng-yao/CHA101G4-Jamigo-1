package com.jamigo.shop.shopCarousel.model;

import java.sql.Date;
import java.util.List;

public interface ShopCarouselService {

	// ================== 查所有 ======================
	List<ShopCarouselVO> getAll();

	// ================== 新增 ======================
	ShopCarouselVO addCarousel(String shopCarouselTitle, String shopCarouselText, byte[] shopCarouselPic,
			Date shopCarouselStartTime, Date shopCarouselEndTime, Integer shopCarouselState, String shopCarouselUrl);

	ShopCarouselVO getOneShopCarousel(Integer ShopCarouselNo);

	void updateShopCarousel(Integer shopCarouselNo, String shopCarouselTitle, String shopCarouselText,
			byte[] shopCarouselPic, Date shopCarouselStartTime, Date shopCarouselEndTime, Integer shopCarouselState,
			String shopCarouselUrl);

	void delete(Integer shopCarouselNo);

}