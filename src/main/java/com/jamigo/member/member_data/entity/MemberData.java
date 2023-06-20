package com.jamigo.member.member_data.entity;

import com.jamigo.member.member_data.core.Core;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "member_data")
public class MemberData extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberNo", nullable = false, insertable = false, updatable = false)
    private Integer memberNo;

    @Column(name = "memberAccount", nullable = false, length = 20, updatable = false)
    private String memberAccount;

    @Column(name = "memberName", nullable = false, length = 20)
    private String memberName;

    @Builder.Default
    @Column(name = "memberGender")
    private Byte memberGender = null;

    @Column(name = "memberPassword", nullable = false, length = 20)
    private String memberPassword;

    @Column(name = "memberPhone", nullable = false, length = 10)
    private String memberPhone;

    @Column(name = "memberEmail", nullable = false, length = 40)
    private String memberEmail;

    @Column(name = "memberAddress", length = 100, insertable = false)
    private String memberAddress ;

    @Column(name = "memberJoinTime", nullable = false, insertable = false, updatable = false)
    private Timestamp memberJoinTime;

    @Column(name = "memberBirthday", insertable = false)
    private Timestamp memberBirthday ;

    @Builder.Default
    @Column(name = "memberNation", length = 10, updatable = false)
    private String memberNation = "台灣";

    @Column(name = "memberPic", insertable = false)
    private byte[] memberPic ;

    @Column(name = "memberPicString", insertable = false)
    private String memberPic4json ;

    @Column(name = "memberCard", length = 19, insertable = false)
    private String memberCard ;

    @Builder.Default
    @Column(name = "memberPoints", nullable = false)
    private Integer memberPoints = 0;

    @Builder.Default
    @Column(name = "levelNo", nullable = false)
    private Byte levelNo=1;

    @Builder.Default
    @Column(name = "memberStat", nullable = false)
    private Byte memberStat = 0;

}