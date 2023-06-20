package com.jamigo.counter.ctrl.counterCtrlModel;

import java.util.List;

public class CounterCtrlService {
private CounterCtrlDao CounterCtrlDao;
	
	public CounterCtrlService() {
		CounterCtrlDao = new CounterCtrlDaoImpl();
	}
	public void updateCounter(Integer counterNo, Float cutPercent, String counterAccount, byte counterStat) {
		CounterCtrlDTO counterDTO = new CounterCtrlDTO();
		counterDTO.setCutPercent(cutPercent);
		counterDTO.setCounterAccount(counterAccount);
		counterDTO.setCounterStat(counterStat);
		counterDTO.setCounterNo(counterNo);
		
		CounterCtrlDao.update(counterDTO);
	}
	public void insertCounter(Float cutPercent, String counterAccount, byte counterStat) {
		CounterCtrlDTO counterDTO = new CounterCtrlDTO();
		counterDTO.setCutPercent(cutPercent);
		counterDTO.setCounterAccount(counterAccount);
		counterDTO.setCounterStat(counterStat);
		
		CounterCtrlDao.insert(counterDTO);
	}
	public CounterCtrlVO getOneCounter(Integer counterno) {
		return CounterCtrlDao.findByPrimaryKey(counterno);
	}
	public List<CounterCtrlVO> getPartInfoForTable() {
		return CounterCtrlDao.getPartInfo();
	}
	public List<CounterCtrlVO> getAllCounter() {
		return CounterCtrlDao.getAll();
	}
	
	public CounterCtrlDTO getByAccount(Integer counterNo, String counterAccount) {
		return CounterCtrlDao.getByAccount(counterNo, counterAccount);
	}
	public CounterCtrlDTO getByAccount(String counterAccount) {
		return CounterCtrlDao.getByAccount(counterAccount);
	}
}
