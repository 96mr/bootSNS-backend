package com.spring.boot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SequenceGenerator(name="MEMBER_SEQ", sequenceName="MEMBER_NO_SEQ", allocationSize = 1)
@Entity
@Table(name="MEMBER", schema="EX1")
public class Member {
	@Id
	@Column(name="user_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEMBER_SEQ")
	private long userNo;
	private String id;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date regdate;
	private String birth;
	private String phone;
	private String email;
	private String privacy;
	
	@OneToOne(mappedBy="member", fetch = FetchType.LAZY)
	private Profile profile;

	@OneToMany(mappedBy="mid")
	private List<Follow> following = new ArrayList<>();
	@OneToMany(mappedBy="tid")
	private List<Follow> follower = new ArrayList<>();
	
	@Builder
	public Member(long userNo, String id, String password, Date regdate, String birth, String phone, String email, String privacy) {
		this.userNo = userNo;
		this.id = id;
		this.password = password;
		this.regdate = regdate;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.privacy = privacy;
	}
	
}
