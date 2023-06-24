package com.jamigo.counter.counterCarousel.counterCarouselModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CounterCarouselRepository extends JpaRepository<CounterCarouselVO, Integer> {
	
	List<CounterCarouselVO> findAllByCounterNo(Integer counterNo);
	
	@Transactional
	@Modifying
	@Query(value = "delete from counter_carousel where counterCarouselNo =:counterCarouselNo", nativeQuery = true)
	void deleteById(Integer counterCarouselNo);
}
