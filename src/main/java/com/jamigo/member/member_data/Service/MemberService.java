package com.jamigo.member.member_data.Service;

import com.jamigo.member.member_data.entity.MemberData;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService  {

	MemberData register(MemberData memberData);

	MemberData login(MemberData memberData);

	MemberData edit(MemberData memberData);

	List<MemberData> findAll();

	MemberData selectById(MemberData memberData)    ;

	boolean remove(Integer id);

	boolean save(MemberData member);
}