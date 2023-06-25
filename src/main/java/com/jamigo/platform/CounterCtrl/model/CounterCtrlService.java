package com.jamigo.platform.CounterCtrl.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface CounterCtrlService {
	
	public void updateCounter(Integer counterNo, Float cutPercent, String counterAccount, byte counterStat);
	public void insertCounter(Float cutPercent, String counterAccount, byte counterStat);
	public CounterCtrlVO getOneCounter(Integer counterno);
	public List<CounterCtrlVO> getPartInfoForTable();
	public List<CounterCtrlVO> getAllCounter();
	
	public CounterCtrlDTO getByAccount(Integer counterNo, String counterAccount);
	public CounterCtrlDTO getByAccount(String counterAccount);
}
