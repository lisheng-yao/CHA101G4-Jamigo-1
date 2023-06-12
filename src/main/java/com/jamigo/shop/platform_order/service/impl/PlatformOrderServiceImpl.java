package com.jamigo.shop.platform_order.service.impl;

import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.member.member_data.dto.MemberDataDTO;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformOrderServiceImpl implements PlatformOrderService {

    @Autowired
    private MemberDataDAO memberDataDAO;

    @Override
    public MemberDataDTO getMemberData() {
        MemberData memberData = memberDataDAO.selectById(1);
        MemberDataDTO memberDataDTO = new MemberDataDTO();

        memberDataDTO.setMemberName(memberData.getMemberName());
        memberDataDTO.setMemberPhone(memberData.getMemberPhone());
        memberDataDTO.setMemberEmail(memberData.getMemberEmail());
        return memberDataDTO;
    }
}
