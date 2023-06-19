package com.jamigo.member.member_data.dao;

import com.jamigo.member.member_data.entity.MemberData;
import org.hibernate.Session;

import java.util.List;

public interface MemberDataDAO {
    MemberData selectBymemberAccount(String memberAccount);

    MemberData selectForLogin(String username, String password);
    int insert(MemberData memberData);

    int deleteById(Integer memberNo);

    int update(MemberData memberData);

    MemberData  selectById(Integer memberNo);

    List<MemberData> selectAll();
}
