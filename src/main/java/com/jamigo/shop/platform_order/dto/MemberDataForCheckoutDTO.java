package com.jamigo.shop.platform_order.dto;

import com.jamigo.member.member_level.model.MemberLevelDetail;
import lombok.Getter;
import lombok.Setter;

// 用於結帳頁面的 DTO
@Getter
@Setter
public class MemberDataForCheckoutDTO {

    private String memberName;  // 會員姓名
    private String memberPhone;  // 會員手機號碼
    private String memberEmail;  // 會員Email
    private String memberAddress;  // 會員住址
    private MemberLevelDetail memberLevelDetail;  // 會員等級資訊
}
