package com.jamigo.shop.shopCarousel.model;

import java.util.List;

public interface ShopCarouselDAO {
		public void insert(ShopCarouselVO shopCarouselVO);
		public ShopCarouselVO findByPrimaryKey(Integer shopCarouselNo);
		public void update(ShopCarouselVO shopCarouselVO);
		public List<ShopCarouselVO> getAll();

}
