package com.jamigo.counter.counterCarousel.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamigo.counter.counterCarousel.entity.CounterCarouselService;

@Controller
@RequestMapping("/counterCarouselImg")
public class CounterCarouselReaderControll {
	
	@Autowired
	private CounterCarouselService server;
	
	@GetMapping("/{counterCarouselNo}")
	public void counterCtrlReader(@PathVariable Integer counterCarouselNo, HttpServletResponse resp) {
		
		try {
//			CounterCarouselService server = new CounterCarouselService();
			byte[] fileContent = server.getById(counterCarouselNo).getCounterCarouselPic();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			resp.getOutputStream().write(fileContent);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			try {
				// 抓取NullPointerException
				resp.sendRedirect("/Jamigo/platform/counter_ctrl/image-nodata/111.jpg");
//				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
