package com.jamigo.platform.CounterCtrl.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamigo.counter.counterCarousel.entity.CounterCarouselService;

@Service
public class CounterCtrlServiceImp implements CounterCtrlService {

	@Autowired
	private CounterCtrlDao counterCtrlDao;
	
	public void updateCounter(Integer counterNo, Float cutPercent, String counterAccount, byte counterStat) {
		CounterCtrlDTO counterDTO = new CounterCtrlDTO();
		counterDTO.setCutPercent(cutPercent);
		counterDTO.setCounterAccount(counterAccount);
		counterDTO.setCounterStat(counterStat);
		counterDTO.setCounterNo(counterNo);
		
		counterCtrlDao.update(counterDTO);
	}
	public void insertCounter(Float cutPercent, String counterAccount, byte counterStat) {
		CounterCtrlDTO counterDTO = new CounterCtrlDTO();
		counterDTO.setCutPercent(cutPercent);
		counterDTO.setCounterAccount(counterAccount);
		counterDTO.setCounterStat(counterStat);
		
		counterCtrlDao.insert(counterDTO);
	}
	public CounterCtrlVO getOneCounter(Integer counterno) {
		return counterCtrlDao.findByPrimaryKey(counterno);
	}
	public List<CounterCtrlVO> getPartInfoForTable() {
		return counterCtrlDao.getPartInfo();
	}
	public List<CounterCtrlVO> getAllCounter() {
		return counterCtrlDao.getAll();
	}
	
	public CounterCtrlDTO getByAccount(Integer counterNo, String counterAccount) {
		return counterCtrlDao.getByAccount(counterNo, counterAccount);
	}
	public CounterCtrlDTO getByAccount(String counterAccount) {
		return counterCtrlDao.getByAccount(counterAccount);
	}
}
