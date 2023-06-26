package com.jamigo.platform.CounterCtrl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.platform.CounterCtrl.model.CounterCtrlDTO;
import com.jamigo.platform.CounterCtrl.model.CounterCtrlService;
import com.jamigo.platform.CounterCtrl.model.CounterCtrlVO;

@RestController
@RequestMapping("/counterCtrl")
public class CounterCtrlServlet {
	
	@Autowired
	private CounterCtrlService service;
	
	@GetMapping("/getAll")
	public List<CounterCtrlVO> getAll() {
		List<CounterCtrlVO> CounterCtrlAll = service.getAllCounter();
		return CounterCtrlAll;
	}
	
	@GetMapping("/getCounterById/{counterNo}")
	public CounterCtrlVO getCounterById(@PathVariable Integer counterNo) {
		CounterCtrlVO CounterCtrl = service.getOneCounter(counterNo);
		return CounterCtrl;
	}
	
	@GetMapping("/getPartInfo")
	public List<CounterCtrlVO> getPartInfo() {
		List<CounterCtrlVO> CounterCtrlPartInfo = service.getPartInfoForTable();
		return CounterCtrlPartInfo;
	}
	
	
	@PostMapping("/insert")
	public String insert(@RequestBody CounterCtrlDTO counterCtrlDTO) {
		service.insertCounter(counterCtrlDTO.getCutPercent(), counterCtrlDTO.getCounterAccount(), counterCtrlDTO.getCounterStat());
		return "success";
	}
	
	@PostMapping("/update")
	public String update(@RequestBody CounterCtrlDTO CounterCtrlDTO) {
		service.updateCounter(CounterCtrlDTO.getCounterNo(), CounterCtrlDTO.getCutPercent(), CounterCtrlDTO.getCounterAccount(), CounterCtrlDTO.getCounterStat());
		return "success";
	}
	
	@PostMapping("/findByAccount/{counterNo}/{account}")
	public Boolean findByAccount(@PathVariable Integer counterNo, @PathVariable String account) {
		Boolean flag = true;
//		System.out.println(CounterCtrlService.getByAccount(counterNo, account));
		if (service.getByAccount(counterNo, account) == null)
			flag = true;
		else
			flag = false;
		
//		System.out.println(flag);
		return flag;
	}
	@PostMapping("/findByAccount/{account}")
	public Boolean findByAccount(@PathVariable String account) {
		Boolean flag = true;
//		System.out.println(CounterCtrlService.getByAccount(account));
		if (service.getByAccount(account) == null)
			flag = true;
		else
			flag = false;
		
//		System.out.println(flag);
		return flag;
	}
	
}
