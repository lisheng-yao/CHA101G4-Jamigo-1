package com.jamigo.member.member_data.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * 用於結帳時，自動填入訂購人資料
 */
@Getter
@Setter
public class MemberDataForCheckoutDTO {

    // 會員姓名
    private String memberName;
    // 會員手機
    private String memberPhone;
    // 會員Email
    private String memberEmail;
}
