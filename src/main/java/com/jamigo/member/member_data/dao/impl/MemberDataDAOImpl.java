package com.jamigo.member.member_data.dao.impl;

import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.member.member_data.entity.MemberData;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

/**
 * 日後即使換成鈞閔的版本，也不會影響豪哥程式的運行
 */
@Repository
public class MemberDataDAOImpl implements MemberDataDAO {

    @PersistenceContext
    private Session session;

    @Override
    public MemberData selectById(Integer id) {
        return session.get(MemberData.class, id);
    }
}
