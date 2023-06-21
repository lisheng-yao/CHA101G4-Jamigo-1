package com.jamigo.counter.ctrl.counterCtrlModel;

import java.util.List;

public interface CounterCtrlDao {
	public void insert(CounterCtrlDTO counterDTO);
    public void update(CounterCtrlDTO counterDTO);
    public void delete(Integer counterno);
    public CounterCtrlVO findByPrimaryKey(Integer counterno);
    public List<CounterCtrlVO> getPartInfo();
    public List<CounterCtrlVO> getAll();
    public CounterCtrlDTO getByAccount(Integer counterNo, String counterAccount);
    public CounterCtrlDTO getByAccount(String counterAccount);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
