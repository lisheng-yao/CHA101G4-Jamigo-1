package com.jamigo.shop.shopCarousel.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCarouselServiceImpl implements ShopCarouselService {
	@Autowired
	private ShopCarouselDAO dao;
	
//	public ShopCarouselService() {
//        dao = new ShopCarouselDAOimpl();
//    }
	
//	================== 查所有 ======================
	@Override
	public List<ShopCarouselVO> getAll() {
        return dao.getAll();
    }
	
	
//	================== 新增 ======================
	@Override
	public ShopCarouselVO addCarousel(String shopCarouselTitle,String shopCarouselText,
									byte[] shopCarouselPic,Date shopCarouselStartTime,Date shopCarouselEndTime,
									Integer shopCarouselState, String shopCarouselUrl) {
		
		ShopCarouselVO shopCarouselVO = new ShopCarouselVO();
		
		shopCarouselVO.setShopCarouselTitle(shopCarouselTitle);
		shopCarouselVO.setShopCarouselText(shopCarouselText);
		shopCarouselVO.setShopCarouselPic(shopCarouselPic);
		shopCarouselVO.setShopCarouselStartTime(shopCarouselStartTime);
		shopCarouselVO.setShopCarouselEndTime(shopCarouselEndTime);
		shopCarouselVO.setShopCarouselState(shopCarouselState);
		shopCarouselVO.setShopCarouselUrl(shopCarouselUrl);
		dao.insert(shopCarouselVO);
		
		return shopCarouselVO;
	}

//	================== 查單一 ======================	
	
	@Override
	public ShopCarouselVO getOneShopCarousel(Integer ShopCarouselNo) {
		return dao.findByPrimaryKey(ShopCarouselNo);
	}

	
//	================== 更新 ======================		
	
	@Override
	public void updateShopCarousel(Integer shopCarouselNo,String shopCarouselTitle,String shopCarouselText,
			byte[] shopCarouselPic,Date shopCarouselStartTime,Date shopCarouselEndTime,
			Integer shopCarouselState, String shopCarouselUrl) {
		
		ShopCarouselVO shopCarouselVO = new ShopCarouselVO();
		
		shopCarouselVO.setShopCarouselNo(shopCarouselNo);
		shopCarouselVO.setShopCarouselTitle(shopCarouselTitle);
		shopCarouselVO.setShopCarouselText(shopCarouselText);
		shopCarouselVO.setShopCarouselPic(shopCarouselPic);
		shopCarouselVO.setShopCarouselStartTime(shopCarouselStartTime);
		shopCarouselVO.setShopCarouselEndTime(shopCarouselEndTime);
		shopCarouselVO.setShopCarouselState(shopCarouselState);
		shopCarouselVO.setShopCarouselUrl(shopCarouselUrl);
		
		dao.update(shopCarouselVO);
		
//		return dao.findByPrimaryKey(shopCarouselNo);
	}
}
