package com.jamigo.counter.counterCarousel.counterCarouselModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterCarouselService {
	
	@Autowired
	CounterCarouselRepository repository;
	
	public void update(CounterCarouselDTO counterCarouselDTO) {
		repository.save(counterCarouselDTO);
	}

	public void add(CounterCarouselDTO counterCarouselDTO) {
		repository.save(counterCarouselDTO);
	}
	
	public CounterCarouselDTO getById(Integer counterCarouselNo) {
		Optional<CounterCarouselDTO> optional = repository.findById(counterCarouselNo);
		return optional.get();
	}
	
	public List<CounterCarouselDTO> getAll() {
		return repository.findAll();
	}
	
}
