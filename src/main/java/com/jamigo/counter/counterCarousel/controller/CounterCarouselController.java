package com.jamigo.counter.counterCarousel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamigo.counter.counterCarousel.entity.CounterCarouselDTO;
import com.jamigo.counter.counterCarousel.entity.CounterCarouselService;
import com.jamigo.counter.counterCarousel.entity.CounterCarouselVO;

@RestController
@RequestMapping("/counterCarousel")
public class CounterCarouselController {
	
	@Autowired
	private CounterCarouselService service;
	
	@GetMapping("/getAllByCounterNo/{counterNo}")
	public List<CounterCarouselVO> getAllByCounterNo(@PathVariable Integer counterNo) {
		return service.getAllByCounterNo(counterNo);
	}
	
	@GetMapping("/getAllByCounterNoWithoutPic/{counterNo}")
	public List<CounterCarouselDTO> getAllByCounterNoWithoutPic(@PathVariable Integer counterNo) {
		List<CounterCarouselVO> voList = service.getAllByCounterNo(counterNo);
		List<CounterCarouselDTO> dtoList = new ArrayList<>();
		for(CounterCarouselVO vo : voList) {
			CounterCarouselDTO dto = new CounterCarouselDTO();
			dto.setCounterCarouselNo(vo.getCounterCarouselNo());
			dto.setCounterNo(vo.getCounterNo());
			dto.setCounterCarouselText(vo.getCounterCarouselText());
			dto.setCounterCarouselStartTime(vo.getCounterCarouselStartTime());
			dto.setCounterCarouselEndTime(vo.getCounterCarouselEndTime());
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@GetMapping("/getById/{counterNo}")
	public CounterCarouselVO getById(@PathVariable Integer counterCarouselNo) {
		return service.getById(counterCarouselNo);
	}
	
	@GetMapping("/getByIdWithoutPic/{counterCarouselNo}")
	public CounterCarouselDTO getByIdWithoutPic(@PathVariable Integer counterCarouselNo) {
		CounterCarouselVO vo = service.getById(counterCarouselNo);
		CounterCarouselDTO dto = new CounterCarouselDTO();
		dto.setCounterCarouselNo(vo.getCounterCarouselNo());
		dto.setCounterNo(vo.getCounterNo());
		dto.setCounterCarouselText(vo.getCounterCarouselText());
		dto.setCounterCarouselStartTime(vo.getCounterCarouselStartTime());
		dto.setCounterCarouselEndTime(vo.getCounterCarouselEndTime());
		return dto;
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody CounterCarouselVO counterCarouselVO) {
		service.update(counterCarouselVO);
		return ResponseEntity.ok(counterCarouselVO.getCounterCarouselNo());
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody CounterCarouselVO counterCarouselVO) {
		if(counterCarouselVO.getCounterCarouselPic() == null) {
			CounterCarouselVO originVo = service.getById(counterCarouselVO.getCounterCarouselNo());
			counterCarouselVO.setCounterCarouselPic(originVo.getCounterCarouselPic());
		}
		service.update(counterCarouselVO);
		return ResponseEntity.ok(counterCarouselVO.getCounterCarouselNo());
	}
	
	@PostMapping("/insertImg")
	public ResponseEntity<?> insertImg(@RequestPart("counterCarouselPic") MultipartFile counterCarouselPic,
									   @RequestPart("counterCarouselNo") String counterCarouselNo) {
		try {
			CounterCarouselVO counterCarouselVO = service.getById(Integer.parseInt(counterCarouselNo));
			counterCarouselVO.setCounterCarouselPic(counterCarouselPic.getBytes());
			service.add(counterCarouselVO);
			return ResponseEntity.ok("上傳圖片成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上船失敗");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上船失敗");
		}
	}
	
	@GetMapping("/deleteById/{counterCarouselNo}")
	public ResponseEntity<?> deleteById(@PathVariable Integer counterCarouselNo) {
		service.deleteById(counterCarouselNo);
		return ResponseEntity.ok("刪除成功");
	}
}
