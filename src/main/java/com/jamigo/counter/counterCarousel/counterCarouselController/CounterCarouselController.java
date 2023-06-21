package com.jamigo.counter.counterCarousel.counterCarouselController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.counter.counterCarousel.counterCarouselModel.CounterCarouselDTO;
import com.jamigo.counter.counterCarousel.counterCarouselModel.CounterCarouselService;

@RestController
@RequestMapping("/counterCarousel")
public class CounterCarouselController {
	
	@Autowired
	CounterCarouselService service;
	
	@GetMapping("/getAll")
	public List<CounterCarouselDTO> getAll() {
		return service.getAll();
	}
}
