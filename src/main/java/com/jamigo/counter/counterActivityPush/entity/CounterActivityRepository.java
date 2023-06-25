package com.jamigo.counter.counterActivityPush.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterActivityRepository extends JpaRepository<CounterActivityPushEntity, Integer> {

}
