package com.jamigo.member.member_data.dto;

import com.jamigo.member.member_level.model.MemberLevelDetail;

// 用於結帳頁面的 DTO
public class MemberDataForCheckoutDTO {
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
    private MemberLevelDetail memberLevelDetail;

    // getters and setters
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public MemberLevelDetail getMemberLevelDetail() {
        return memberLevelDetail;
    }

    public void setMemberLevelDetail(MemberLevelDetail memberLevelDetail) {
        this.memberLevelDetail = memberLevelDetail;
    }
}
