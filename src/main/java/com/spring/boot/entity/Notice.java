package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "NOTICE_SEQ", sequenceName = "NOTICE_NO_SEQ", allocationSize = 1)
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="NOTICE", schema="EX1")
public class Notice {
	@Id
	@Column(name = "noteno")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOTICE_SEQ")
	private long noteNo;
	@Column(name = "m_id")
	private long sender;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="t_id")
	private Member receiver; 
	@Enumerated(EnumType.STRING)
	private NotificationType notetype;
	private long bno;
	private String content; //댓글 내용
	private String noteurl;
	private Date regdate;
	private Date chkdate; //알림 확인 날짜
	@Column(name = "del_chk")
	private String delChk;
	
	@Builder
	public Notice(long noteNo, long sender, Member receiver, NotificationType notetype, long bno, String content, String noteurl, Date regdate,
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
	
	public void updateChkdate() {
		this.chkdate = new Date();
	}
}
