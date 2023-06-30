package com.jamigo.counter.counter.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamigo.counter.counter.entity.Counter;


public interface CounterRepository extends JpaRepository<Counter, Integer>{

		Counter findByCounterAccountAndCounterPassword(String counterAccount , String counterPassword);
		Optional<Counter> findByCounterEmail(String counterEmail);
}
