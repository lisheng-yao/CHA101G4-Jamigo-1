package com.jamigo.member.member_data.dao;

import com.jamigo.member.member_data.entity.MemberData;


/**
 * 日後即使換成鈞閔的版本，也不會影響豪哥程式的運行
 */
public interface MemberDataDAO {
    MemberData selectById(Integer id);
}
