package com.jamigo.member.member_data.dto;

// 用於結帳頁面的 DTO
public class MemberDataForCheckoutDTO {
    private String memberName;
    private String memberPhone;
    private String memberEmail;

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
}
