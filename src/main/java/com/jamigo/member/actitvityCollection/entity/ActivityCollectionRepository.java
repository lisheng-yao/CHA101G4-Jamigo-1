package com.jamigo.member.actitvityCollection.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityCollectionRepository extends JpaRepository<ActivityCollectionEntity, ActivityCollectionKey> {
	List<ActivityCollectionEntity> findByMemberNo(Integer memberNo);
}
