package com.jamigo.shop.counter_order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CounterOrderForTableDTO {

    private Integer counterOrderNo;

    private Integer platformOrderNo;

    private Integer counterNo;

    private Integer totalPaid;

    private Integer actuallyPaid;

    private Byte counterOrderStat;

    private Byte disbursementStat;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp orderTime;

    public CounterOrderForTableDTO(Integer counterOrderNo, Integer platformOrderNo, Integer counterNo, Integer totalPaid, Integer actuallyPaid, Byte counterOrderStat, Byte disbursementStat) {
        this.counterOrderNo = counterOrderNo;
        this.platformOrderNo = platformOrderNo;
        this.counterNo = counterNo;
        this.totalPaid = totalPaid;
        this.actuallyPaid = actuallyPaid;
        this.counterOrderStat = counterOrderStat;
        this.disbursementStat = disbursementStat;
    }
}
