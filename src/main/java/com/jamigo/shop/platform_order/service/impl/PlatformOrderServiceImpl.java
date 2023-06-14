package com.jamigo.shop.platform_order.service.impl;

import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.member.member_data.dto.MemberDataForCheckoutDTO;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.cart.dao.CartDAO;
import com.jamigo.shop.cart.dto.CartForCheckoutDTO;
import com.jamigo.shop.platform_order.dao.PlatformOrderDAO;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.service.PlatformOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlatformOrderServiceImpl implements PlatformOrderService {

    @Autowired
    private MemberDataDAO memberDataDAO;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private PlatformOrderDAO platformOrderDAO;

    @Override
    public MemberDataForCheckoutDTO fillInMemberData(Integer memberNo) {
        MemberData memberData = memberDataDAO.selectById(memberNo);
        MemberDataForCheckoutDTO memberDataForCheckoutDTO = new MemberDataForCheckoutDTO();

        memberDataForCheckoutDTO.setMemberName(memberData.getMemberName());
        memberDataForCheckoutDTO.setMemberPhone(memberData.getMemberPhone());
        memberDataForCheckoutDTO.setMemberEmail(memberData.getMemberEmail());
        return memberDataForCheckoutDTO;
    }
    @Override
    public Map<String, List<CartForCheckoutDTO>> getCartInfo(Integer memberNo) {
        return cartDAO.selectByMemberNo(memberNo);
    }

    @Override
    public List<PlatformOrder> findAll() {
        return platformOrderDAO.selectAll();
    }
}
