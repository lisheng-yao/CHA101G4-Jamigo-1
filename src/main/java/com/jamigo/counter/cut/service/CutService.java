package com.jamigo.counter.cut.service;

import com.jamigo.counter.cut.dto.CutForCounterDTO;
import com.jamigo.counter.cut.dto.CutForPlatformDTO;
import com.jamigo.counter.cut.entity.Cut;

import java.sql.Timestamp;
import java.util.List;

public interface CutService {

    List<CutForPlatformDTO> getAllCutData();

    List<CutForCounterDTO> getCounterAllCutData(Integer counterNo);

    void calculateMonthlyIncome(Timestamp start, Timestamp end, Timestamp monthForCut);
}
