package com.spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="PROFILE", schema="EX1")
public class Profile {
	@Id
	@Column(name="user_no")
	private int userNo;
	@NotBlank
	private String name;
	private String introduce;
	@OneToOne
	@JoinColumn(name="image")
	private FileInfo image;
	
	@OneToOne
	@JoinColumn(name="user_no")
	@MapsId
	private Member member;
	
	@Builder
	public Profile(int userNo, String name, String introduce, FileInfo image) {
		this.userNo = userNo;
		this.name = name;
		this.introduce = introduce;
		this.image = image;
	}
	
	public void update(String name, String introduce) {
		this.name = name;
		this.introduce = introduce;
	}
	
}
