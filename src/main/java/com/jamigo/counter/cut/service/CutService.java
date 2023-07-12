package com.jamigo.counter.cut.service;

import com.jamigo.counter.cut.dto.CutForCounterDTO;
import com.jamigo.counter.cut.dto.CutForPlatformDTO;
import com.jamigo.counter.cut.entity.Cut;
import com.jamigo.shop.counter_order.entity.CounterOrder;

import java.sql.Timestamp;
import java.util.List;

public interface CutService {

    List<CutForPlatformDTO> getAllCutData();

    List<CutForCounterDTO> getCounterAllCutData(Integer counterNo);

    void calculateMonthlyIncome();

    void calculateAndSaveCutForCounter(Integer counterNo, List<CounterOrder> orders, Timestamp monthForCut);

//    void calculateMissingMonthlyIncome();
}
