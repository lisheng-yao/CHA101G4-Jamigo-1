package com.jamigo.member.member_data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "member_data")
public class MemberData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer memberNo;

    @Column(nullable = false, length = 20)
    private String memberAccount;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(nullable = false)
    private Byte memberGender;

    @Column(nullable = false, length = 20)
    private String memberPassword;

    @Column(nullable = false, length = 10)
    private String memberPhone;

    @Column(nullable = false, length = 40)
    private String memberEmail;

    @Column(length = 100)
    private String memberAddress;

    @Column(nullable = false)
    private Instant memberJoinTime;

    @Column(nullable = false)
    private Byte levelNo;

    private Instant memberBirthday;

    @Column(length = 10)
    private String memberNation;

    private byte[] memberPic;

    @Column(length = 19)
    private String memberCard;

    @Column(nullable = false)
    private Integer memberPoints;

    @Column(nullable = false)
    private Byte memberStat;
}