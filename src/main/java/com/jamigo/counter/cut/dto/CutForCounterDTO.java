package com.jamigo.counter.cut.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CutForCounterDTO {

    private Integer cutNo;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp monthlyTime;

    private Byte cutPayStat;

    private Integer monthly;

    private Float cutPercent;

    private Integer cutMoney;

    private Integer cutMonthly;
}
