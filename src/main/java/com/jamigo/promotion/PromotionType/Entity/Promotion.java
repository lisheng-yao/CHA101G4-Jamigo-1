package com.jamigo.promotion.PromotionType.Entity;

import com.jamigo.member.member_data.core.Core;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion extends Core {
    @Id
    @Column(name = "promotionName", nullable = false, length = 10)
    private String promotionName;

    @Column(name = "promotionType", nullable = false, length = 20)
    private String promotionType;

    @Column(name = "promotionMethod", nullable = false, length = 20)
    private String promotionMethod;

    @Column(name = "adminNo")
    private Integer adminNo;

    @Column(name = "counterNo")
    private Integer counterNo;

}