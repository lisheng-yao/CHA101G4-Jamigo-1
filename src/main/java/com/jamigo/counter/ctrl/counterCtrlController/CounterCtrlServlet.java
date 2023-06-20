package com.jamigo.counter.ctrl.counterCtrlController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.counter.ctrl.counterCtrlModel.CounterCtrlDTO;
import com.jamigo.counter.ctrl.counterCtrlModel.CounterCtrlService;
import com.jamigo.counter.ctrl.counterCtrlModel.CounterCtrlVO;

@RestController
@RequestMapping("/counterCtrl")
public class CounterCtrlServlet {
	
	@GetMapping("/getAll")
	public List<CounterCtrlVO> getAll() {
		CounterCtrlService CounterCtrlService = new CounterCtrlService();
		List<CounterCtrlVO> CounterCtrlAll = CounterCtrlService.getAllCounter();
		return CounterCtrlAll;
	}
	
	@GetMapping("/getCounterById/{counterNo}")
	public CounterCtrlVO getCounterById(@PathVariable Integer counterNo) {
		CounterCtrlService CounterCtrlService = new CounterCtrlService();
		CounterCtrlVO CounterCtrl = CounterCtrlService.getOneCounter(counterNo);
		return CounterCtrl;
	}
	
	@GetMapping("/getPartInfo")
	public List<CounterCtrlVO> getPartInfo() {
		CounterCtrlService CounterCtrlService = new CounterCtrlService();
		List<CounterCtrlVO> CounterCtrlPartInfo = CounterCtrlService.getPartInfoForTable();
		return CounterCtrlPartInfo;
	}
	
	
	@PostMapping("/insert")
	public String insert(@RequestBody CounterCtrlDTO counterCtrlDTO) {
		CounterCtrlService CounterCtrlService = new CounterCtrlService();
		CounterCtrlService.insertCounter(counterCtrlDTO.getCutPercent(), counterCtrlDTO.getCounterAccount(), counterCtrlDTO.getCounterStat());
		return "success";
	}
	
	@PostMapping("/update")
	public String update(@RequestBody CounterCtrlDTO CounterCtrlDTO) {
		CounterCtrlService CounterCtrlService = new CounterCtrlService();
		CounterCtrlService.updateCounter(CounterCtrlDTO.getCounterNo(), CounterCtrlDTO.getCutPercent(), CounterCtrlDTO.getCounterAccount(), CounterCtrlDTO.getCounterStat());
		return "success";
	}
	
	@PostMapping("/findByAccount/{counterNo}/{account}")
	public Boolean findByAccount(@PathVariable Integer counterNo, @PathVariable String account) {
		Boolean flag = true;
		CounterCtrlService CounterCtrlService = new CounterCtrlService();
//		System.out.println(CounterCtrlService.getByAccount(counterNo, account));
		if (CounterCtrlService.getByAccount(counterNo, account) == null)
			flag = true;
		else
			flag = false;
		
//		System.out.println(flag);
		return flag;
	}
	@PostMapping("/findByAccount/{account}")
	public Boolean findByAccount(@PathVariable String account) {
		Boolean flag = true;
		CounterCtrlService CounterCtrlService = new CounterCtrlService();
		System.out.println(CounterCtrlService.getByAccount(account));
		if (CounterCtrlService.getByAccount(account) == null)
			flag = true;
		else
			flag = false;
		
//		System.out.println(flag);
		return flag;
	}
	
}
