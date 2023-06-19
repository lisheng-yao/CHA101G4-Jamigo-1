package com.jamigo.member.member_data.Service.impl;



import com.jamigo.member.member_data.Service.MemberService;
import com.jamigo.member.member_data.dao.impl.MemberDataDaoImpl;
import com.jamigo.member.member_data.entity.MemberData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    @Autowired
    public MemberDataDaoImpl dao;

    @Override
    public MemberData register(MemberData memberData) {
        if (memberData.getMemberAccount() == null) {
            memberData.setMessage("帳號未輸入");
            memberData.setSuccessful(false);
            return memberData;
        }

        if (memberData.getMemberPassword() == null) {
            memberData.setMessage("密碼未輸入");
            memberData.setSuccessful(false);
            return memberData;
        }

        if (memberData.getMemberName() == null) {
            memberData.setMessage("姓名未輸入");
            memberData.setSuccessful(false);
            return memberData;
        }
        if (memberData.getMemberPhone() == null) {
            memberData.setMessage("號碼未輸入");
            memberData.setSuccessful(false);
            return memberData;
        }
        if (memberData.getMemberEmail() == null) {
            memberData.setMessage("信箱未輸入");
            memberData.setSuccessful(false);
            return memberData;
        }
//try {//
//	beginTransaction();//
        if (dao.selectBymemberAccount(memberData.getMemberAccount()) != null) {
            memberData.setMessage("帳號重複");
            memberData.setSuccessful(false);
//			rollback();//
            return memberData;
        }

//		memberData.setMemberStat(0);
        final int resultCount = dao.insert(memberData);//執行insert
        if (resultCount < 1) {
            memberData.setMessage("註冊錯誤，請聯絡管理員!");
            memberData.setSuccessful(false);
//			rollback();//
            return memberData;
        }

        memberData.setMessage("註冊成功");
        memberData.setSuccessful(true);
//		commit();//
        return memberData;
//	}catch(Exception e){//
//		rollback();//
//		e.printStackTrace();//
//		return null;//
//	}//
    }

    @Override
    public MemberData login(MemberData memberData) {
        final String MemberAccount = memberData.getMemberAccount();
        final String MemberPassword = memberData.getMemberPassword();

        if (MemberAccount == null) {
            memberData.setMessage("帳號未輸入");
            memberData.setSuccessful(false);
            return memberData;
        }

        if (MemberPassword == null) {
            memberData.setMessage("密碼未輸入");
            memberData.setSuccessful(false);
            return memberData;
        }

        memberData = dao.selectForLogin(MemberAccount, MemberPassword);
        if (memberData == null) {
            memberData = new MemberData();
            memberData.setMessage("帳號或密碼錯誤");
            memberData.setSuccessful(false);
            return memberData;
        }

        memberData.setMessage("登入成功");
        memberData.setSuccessful(true);
        return memberData;
    }

    @Override
    public MemberData edit(MemberData memberData) {
        final MemberData oMember = dao.selectById(memberData.getMemberNo());

        if (memberData.getMemberName() != null) {
            oMember.setMemberName(memberData.getMemberName());
        }
        if (memberData.getMemberGender() != null) {
            oMember.setMemberGender(memberData.getMemberGender());
        }
        if (memberData.getMemberPassword() != null) {
            oMember.setMemberPassword(memberData.getMemberPassword());
        }
        if (memberData.getMemberPhone() != null) {
            oMember.setMemberPhone(memberData.getMemberPhone());
        }
        if (memberData.getMemberEmail() != null) {
            oMember.setMemberEmail(memberData.getMemberEmail());
        }
        if (memberData.getMemberAddress() != null) {
            oMember.setMemberAddress(memberData.getMemberAddress());
        }
        if (memberData.getMemberBirthday() != null) {
            oMember.setMemberBirthday(memberData.getMemberBirthday());
        }
        if (memberData.getMemberNation() != null) {
            oMember.setMemberNation(memberData.getMemberNation());
        }
        System.out.println("有沒有圖傳進來");
        System.out.println(memberData.getMemberPic4json());
        System.out.println(memberData.getMemberPic4json().equals("有傳圖進來"));
        if (memberData.getMemberPic4json().equals("有傳圖進來")) {
            oMember.setMemberPic(memberData.getMemberPic());
            oMember.setMemberPic4json("有圖");
        } else {
            oMember.setMemberPic(oMember.getMemberPic());
            oMember.setMemberPic4json("用舊圖");
        }
        if (memberData.getMemberCard() != null) {
            oMember.setMemberCard(memberData.getMemberCard());
        }
        final int resultCount = dao.update(oMember);
        oMember.setSuccessful(resultCount > 0);
        oMember.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
        return oMember;
    }

    public MemberData selectById(MemberData memberData) {
        MemberData resultData = dao.selectById(memberData.getMemberNo());
        resultData.setSuccessful(true);
        return resultData;
    }

    @Override
    public List<MemberData> findAll() {
        return dao.selectAll();
    }

    @Override
    public boolean remove(Integer id) {

        final int resultCount = dao.deleteById(id);

        return resultCount > 0;

//			return dao.deleteById(id)>0;
    }

    @Override
    public boolean save(MemberData memberData) {
        return dao.update(memberData) > 0;
    }
}
