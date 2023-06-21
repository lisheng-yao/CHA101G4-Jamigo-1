package com.jamigo.counter.counterCarousel.counterCarouselModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CounterCarouselRepository extends JpaRepository<CounterCarouselDTO, Integer> {
	
}
