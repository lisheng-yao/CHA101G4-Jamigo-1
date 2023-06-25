package com.jamigo.counter.counter.service;


import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jamigo.counter.counter.dao.CounterRepository;
import com.jamigo.counter.counter.entity.Counter;



@Service
public class CounterServiceimpl implements CounterService {

	@Autowired
	private CounterRepository counterRepository;

	@Override
	public void updateCounter(Counter data) {
		int counterNo = data.getCounterNo();
		Optional<Counter> counter = counterRepository.findById(counterNo);

		// 更新存在的pk
		var counter2 = counter.orElse(null);
			counter2.setCounterName(data.getCounterName());
//		    counter2.setCutPercent(data.getCutPercent());
//		    counter2.setCounterStat(data.getCounterStat());
		    counter2.setCounterGui(data.getCounterGui());
		    counter2.setCounterFloor(data.getCounterFloor());
		    counter2.setCounterTel(data.getCounterTel());
		    counter2.setCounterPoc(data.getCounterPoc());
		    counter2.setCounterPocPhone(data.getCounterPocPhone());
		    counter2.setCounterPocAddress(data.getCounterPocAddress());
		    counter2.setCounterEmail(data.getCounterEmail());
		    counter2.setCounterBankAccount(data.getCounterBankAccount());
		    counter2.setCounterAbout(data.getCounterAbout());
		    counter2.setCounterPassword(data.getCounterPassword());
		    

		counterRepository.save(counter2);

	}

	@Override
	public Counter getCounterByCounterNo(Integer counterNo) {
		Optional<Counter> counter = counterRepository.findById(counterNo);

		return counter.orElse(null);
	}

	@Override
	public Counter findByAcc(String counterAccount, String counterPassword) {
		
		return counterRepository.findByCounterAccountAndCounterPassword(counterAccount, counterPassword);
		
		

	}

	@Override
	public void updateCounterPic(Integer counterNo, MultipartFile counterPic) throws IOException {
		Optional<Counter> counterOptional = counterRepository.findById(counterNo);

	    if (counterOptional.isEmpty()) {
	        throw new NoSuchElementException("找不到櫃位");
	    }

	    Counter counter = counterOptional.get();

	    // 將 MultipartFile 轉換為 byte[]
	    byte[] counterPicBytes = counterPic.getBytes();
	    counter.setCounterPic(counterPicBytes);

	    counterRepository.save(counter);
	}
		
	}


