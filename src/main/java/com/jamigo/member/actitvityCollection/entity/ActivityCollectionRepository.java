package com.jamigo.member.actitvityCollection.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityCollectionRepository extends JpaRepository<ActivityCollectionEntity, ActivityCollectionKey> {
	List<ActivityCollectionEntity> findByMemberNo(Integer memberNo);
	
	@Query("SELECT a FROM ActivityCollectionEntity a WHERE a.activityNo = :activityNo AND a.memberNo = :memberNo")
	ActivityCollectionEntity findByActivityCollectionEntity(Integer activityNo, Integer memberNo);
//	@Query()
//	void deleteByActivityNo(ActivityCollectionEntity activityCollectionEntity);
}
