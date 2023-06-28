package com.jamigo.promotion.PromotionPoint.Entity;

import com.jamigo.member.member_data.core.Core;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "promotion_point")
public class PromotionPoint extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotionPointNo", nullable = false,insertable = false,updatable = false)
    private Integer promotionPointNo;

    @Size(max = 100)
    @NotNull
    @Column(name = "promotionPointName", nullable = false, length = 100)
    private String promotionPointName;

    @Size(max = 10)
    @NotNull
    @Column(name = "promotionName", nullable = false, length = 10)
    private String promotionName;

    @Size(max = 1000)
    @Column(name = "getPointConditions", length = 1000)
    private String getPointConditions;

    @Column(name = "getPointMag")
    private Double getPointMag;

    @Column(name = "promotionExpireDate", nullable = false)
    private Timestamp promotionExpireDate;

    @Column(name = "promotionEffectiveDate", nullable = false)
    private Timestamp promotionEffectiveDate;

    @Column(name = "promotionCreateDate", nullable = false,insertable = false,updatable = false)
    private Timestamp promotionCreateDate;

    @Column(name = "promotionPic")
    private byte[] promotionPic;

}