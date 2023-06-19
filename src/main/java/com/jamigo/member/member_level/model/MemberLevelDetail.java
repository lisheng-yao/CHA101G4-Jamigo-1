package com.jamigo.member.member_level.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter  
@Setter
@ToString
@Table(name="member_level_detail")
public class MemberLevelDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int levelNo;
	private String levelName;
	private int levelFeedback;

}
