package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SequenceGenerator(name="FILE_SEQ", sequenceName="FILE_NO_SEQ", allocationSize = 1)
@Entity
@Table(name="FILEINFO", schema="EX1")
public class FileInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FILE_SEQ")
	private int fno;
	@Column(name="org_name")
	private String orgName;
	@Column(name="save_name")
	private String saveName;
	@Column(name="f_size")
	private int size;
	@Temporal(TemporalType.DATE)
	private Date regdate;
	@Column(name="reg_id")
	private long memberId;
	
	@Builder
	public FileInfo(int fno, String orgName, String saveName, int size, Date regdate, long memberId) {
		this.fno = fno;
		this.orgName = orgName;
		this.saveName = saveName;
		this.size = size;
		this.regdate = regdate;
		this.memberId = memberId;
	}
	
	public void update(String orgName, String saveName, int size) {
		this.orgName = orgName;
		this.saveName = saveName;
		this.size = size;
	}
}
