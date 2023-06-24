package com.jamigo.counter.counterCarousel.counterCarouselModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterCarouselService {
	
	@Autowired
	CounterCarouselRepository repository;
	
	public void update(CounterCarouselVO counterCarouselVO) {
		repository.save(counterCarouselVO);
	}

	public void add(CounterCarouselVO counterCarouselVO) {
		repository.save(counterCarouselVO);
	}
	
	public CounterCarouselVO getById(Integer counterCarouselNo) {
		Optional<CounterCarouselVO> optional = repository.findById(counterCarouselNo);
		return optional.get();
	}

	public List<CounterCarouselVO> getAllByCounterNo(Integer counterNo) {
		return repository.findAllByCounterNo(counterNo);
	}
	
	public void deleteById(Integer counterCarouselNo) {
		repository.deleteById(counterCarouselNo);
	}
	
}
