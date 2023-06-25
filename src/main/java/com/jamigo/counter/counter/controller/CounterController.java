package com.jamigo.counter.counter.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamigo.counter.counter.dao.CounterRepository;
import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.counter.counter.service.CounterService;



@RestController
public class CounterController {

	@Autowired
	private CounterRepository counterRepository;
	@Autowired
	private CounterService counterService;

	// ========================== 查櫃位資料 ===========================

	@GetMapping("/get/count/{counterNo}")
	public Counter getCounte(@PathVariable Integer counterNo) {

		return counterService.getCounterByCounterNo(counterNo);
	}

	// ========================== 更新櫃位資料 ==========================

	@PutMapping("/update/counter/{counterNo}")
	public ResponseEntity<String> updateCounter(@PathVariable Integer counterNo, @RequestBody Counter data) {
		Optional<Counter> counter = counterRepository.findById(counterNo);

		if (counter == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("counter not found");
		}

		counterService.updateCounter(data);

		return ResponseEntity.ok("counter updated successfully");
	}

	// ========================== 櫃位登入 ==========================

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Counter counter, HttpServletRequest req) {

		Counter counter2 = counterService.findByAcc(counter.getCounterAccount(), counter.getCounterPassword());

		if (counter2 != null) {
			HttpSession session = req.getSession();
			session.setAttribute("counter", counter2);
			return new ResponseEntity<>("登入成功", HttpStatus.OK);

		} else

			return new ResponseEntity<>("登入失敗", HttpStatus.UNAUTHORIZED);
	}

	// ========================== 透過session取得櫃位資訊 ==========================

	@GetMapping("/counter/counterAcc")
	public Counter getSessionAcc(HttpSession session, HttpServletRequest req) {

		// 如果有資料要重新取設定session
		Counter ccc = (Counter) session.getAttribute("counter");
		Integer counterNo = ccc.getCounterNo();
		Counter counter3 = counterService.getCounterByCounterNo(counterNo);

		if (counter3 != null) {
			HttpSession session2 = req.getSession();
			session2.setAttribute("counter", counter3);
		}

		return (Counter) session.getAttribute("counter");
	}

	// =========================== 更新櫃位圖片 ===================================
	
	@PutMapping("/update/counterPic/{counterNo}")
	public ResponseEntity<String> updateCounterPic(@PathVariable Integer counterNo,
			@RequestParam("counterPic") MultipartFile counterPic) {
		try {
			counterService.updateCounterPic(counterNo, counterPic);
			return ResponseEntity.ok("圖片更新成功");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到櫃位");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("圖片更新失敗");
		}
	}

	// ========================== 登出 ==========================

	@GetMapping("/sign_out")
	public String sigout(HttpSession session) {
		// 銷毀session中的KV
		session.removeAttribute("counter");
		return "登出成功";
	}
}
