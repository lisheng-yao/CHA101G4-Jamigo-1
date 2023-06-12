package com.jamigo.member.member_data.dao.impl;


import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.member.member_data.entity.MemberData;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
public class MemberDataDAOImpl_HBN implements MemberDataDAO {

    @PersistenceContext
    private Session session;

    @Override
    public MemberData selectById(Integer id) {
        return session.get(MemberData.class, id);
    }
}
