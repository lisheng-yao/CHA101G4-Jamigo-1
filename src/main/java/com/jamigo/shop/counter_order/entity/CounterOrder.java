package com.jamigo.shop.counter_order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "counter_order")
public class CounterOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer counterOrderNo;

    @Column(nullable = false)
    private Integer platformOrderNo;

    @Column(nullable = false)
    private Integer counterNo;

    @Column(nullable = false)
    private Integer totalPaid;

    @Column(nullable = false)
    private Integer actuallyPaid;

    @Column(nullable = false)
    private Byte counterOrderStat;

    @Column(nullable = false)
    private Byte disbursementStat;

}