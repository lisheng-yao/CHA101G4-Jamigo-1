package com.jamigo.member.member_level.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamigo.member.member_level.model.MemberLevelDetail;





// 繼承 JpaRepository 取得對資料庫操作方法
// JpaRepository 介面本身已經被標記為 @Repository
// Spring Boot 將會自動掃描這些類型並將其註冊為 Spring 的 Bean
public interface MemberLevelDetailRepository extends JpaRepository <MemberLevelDetail, Integer> {

}
