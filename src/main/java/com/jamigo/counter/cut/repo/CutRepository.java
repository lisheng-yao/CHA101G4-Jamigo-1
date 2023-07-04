package com.jamigo.counter.cut.repo;

import com.jamigo.counter.cut.entity.Cut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CutRepository extends JpaRepository<Cut, Integer> {

    List<Cut> findByCounterNo(Integer counterNo);
}
