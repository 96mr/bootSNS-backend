package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "NOTICE_SEQ", sequenceName = "NOTICE_NO_SEQ", allocationSize = 1)
@Entity
@Table(name="MEMBER", schema="EX1")
public class Notice {
	@Id
	@Column(name = "noteno")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOTICE_SEQ")
	private int noteNo;
	@Column(name = "m_id")
	private int sender;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="t_id")
	private Member receiver; 
	private String notetype;
	private int bno;
	private String content;
	private String noteurl;
	private Date regdate;
	private Date chkdate;
	@Column(name = "del_chk")
	private String delChk;
	
	@Builder
	public Notice(int noteNo, int sender, Member receiver, String notetype, int bno, String content, String noteurl, Date regdate,
			Date chkdate, String delChk) {
		this.noteNo = noteNo;
		this.sender = sender;
		this.receiver = receiver;
		this.notetype = notetype;
		this.bno = bno;
		this.content = content;
		this.noteurl = noteurl;
		this.regdate = regdate;
		this.chkdate = chkdate;
		this.delChk = delChk;
	}
	
	
}
