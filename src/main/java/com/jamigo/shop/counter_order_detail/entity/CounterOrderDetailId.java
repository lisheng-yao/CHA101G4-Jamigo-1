package com.jamigo.shop.counter_order_detail.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CounterOrderDetailId implements Serializable {
    private static final long serialVersionUID = -5497255015857011967L;

    @Column(nullable = false)
    private Integer counterOrderNo;

    @Column(nullable = false)
    private Integer productNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CounterOrderDetailId entity = (CounterOrderDetailId) o;
        return Objects.equals(this.productNo, entity.productNo) &&
                Objects.equals(this.counterOrderNo, entity.counterOrderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo, counterOrderNo);
    }
}