package com.jamigo.counter.counter.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer counterNo;

    @Column(nullable = false, length = 40)
    private String counterName;

    @Column(nullable = false)
    private Float cutPercent;

    @Column(nullable = false, length = 20)
    private String counterAccount;

    @Column(nullable = false, length = 20)
    private String counterPassword;

    @Column(nullable = false)
    private Byte counterStat;

    @Column(nullable = false, length = 8)
    private String counterGui;

    @Column(nullable = false)
    private Integer counterFloor;

    @Column(length = 9)
    private String counterTel;

    @Column(length = 20)
    private String counterPoc;

    @Column(length = 10)
    private String counterPocPhone;

    @Column(length = 100)
    private String counterPocAddress;

    @Column(nullable = false, length = 40)
    private String counterEmail;

    @Column(nullable = false, length = 20)
    private String counterBankAccount;

    private Byte counterReportCount;

    @Column(length = 300)
    private String counterAbout;

    private byte[] counterPic;
}