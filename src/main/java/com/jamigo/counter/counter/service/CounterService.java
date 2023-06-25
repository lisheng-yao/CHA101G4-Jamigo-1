package com.jamigo.counter.counter.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.jamigo.counter.counter.entity.Counter;



public interface CounterService {
	
	void updateCounter(Counter counter);
	
	Counter getCounterByCounterNo(Integer counterNo);
	
	Counter findByAcc(String counterAccount , String counterPassword);
	
	void updateCounterPic(Integer counterNo, MultipartFile counterPic) throws IOException;

}
