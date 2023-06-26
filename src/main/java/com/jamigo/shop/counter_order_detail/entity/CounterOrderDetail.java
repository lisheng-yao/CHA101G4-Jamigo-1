package com.jamigo.shop.counter_order_detail.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "counter_order_detail")
public class CounterOrderDetail {
    @EmbeddedId
    private CounterOrderDetailId id;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Byte orderDetailStat;

    @Column(length = 500)
    private String evalContent;

    private Integer evalScore;
}