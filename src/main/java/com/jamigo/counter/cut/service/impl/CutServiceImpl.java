package com.jamigo.counter.cut.service.impl;

import com.jamigo.counter.cut.entity.Cut;
import com.jamigo.counter.cut.repo.CutRepository;
import com.jamigo.counter.cut.service.CutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CutServiceImpl implements CutService {

    @Autowired
    private CutRepository cutRepository;

    @Override
    public List<Cut> getAllCutData() {
        return cutRepository.findAll();
    }

    @Override
    public List<Cut> getCounterAllCutData(Integer counterNo) {
        return cutRepository.findByCounterNo(counterNo);
    }
}
