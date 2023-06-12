package com.jamigo.member.member_data.dao;

import com.jamigo.member.member_data.entity.MemberData;

public interface MemberDataDAO {
    MemberData selectById(Integer id);
}
