package com.jamigo.counter.cut.service;

import com.jamigo.counter.cut.entity.Cut;

import java.util.List;

public interface CutService {

    List<Cut> getAllCutData();

    List<Cut> getCounterAllCutData(Integer counterNo);
}
