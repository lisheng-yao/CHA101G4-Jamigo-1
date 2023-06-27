package com.jamigo.member.member_coupon.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MemberCouponId implements Serializable {
    private static final long serialVersionUID = 4416646066899352153L;
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberCouponNo", nullable = false)
    private Integer memberCouponNo;

    @NotNull
    @Column(name = "couponTypeNo", nullable = false)
    private Integer couponTypeNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberCouponId entity = (MemberCouponId) o;
        return Objects.equals(this.couponTypeNo, entity.couponTypeNo) &&
                Objects.equals(this.memberCouponNo, entity.memberCouponNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couponTypeNo, memberCouponNo);
    }

}