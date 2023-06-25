package com.jamigo.counter.counter.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jamigo.counter.counter.dao.CounterRepository;
import com.jamigo.counter.counter.entity.Counter;



@Controller
public class DBPicReader {
	
	@Autowired
	private CounterRepository counterRepository;
	
	@GetMapping("/counterPic/{counterNo}")
	public void counterPic(@PathVariable Integer counterNo, HttpServletResponse response) throws IOException {

		Counter counter = counterRepository.findById(counterNo).orElse(null);
		
		
		if (counter != null && counter.getCounterPic() != null) {
			response.setContentType("image/jpg");
			response.getOutputStream().write(counter.getCounterPic());
		}
	}
}







