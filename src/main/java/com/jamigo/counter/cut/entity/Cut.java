package com.jamigo.counter.cut.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "cut")
public class Cut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer cutNo;

    @Column(nullable = false)
    private Integer counterNo;

    private Integer monthly;

    private Integer cutMonthly;

    @Column(nullable = false)
    private Byte cutPayStat;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp monthlyTime;

}