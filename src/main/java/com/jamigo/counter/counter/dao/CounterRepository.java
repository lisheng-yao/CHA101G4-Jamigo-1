package com.jamigo.counter.counter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamigo.counter.counter.entity.Counter;


public interface CounterRepository extends JpaRepository<Counter, Integer>{

		Counter findByCounterAccountAndCounterPassword(String counterAccount , String counterPassword);
}
