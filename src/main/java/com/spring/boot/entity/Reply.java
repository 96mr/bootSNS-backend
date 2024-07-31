package com.spring.boot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name="REPLY_SEQ", sequenceName="REPLY_NO_SEQ", allocationSize = 1)
@DynamicInsert
@Entity
@Table(name="BOARD_REPLY", schema="EX1")
public class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPLY_SEQ")
	private Long repno;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bno")
	private Board bno;
	private String content; //500
	private String regdate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="writer_id") 
	private Member writerId; 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Reply parentId;
	@Column(name="del_chk")
	private String delChk;
	
	@OneToMany(mappedBy="parentId")
	private List<Reply> children = new ArrayList<>();
	
	
	@Builder
	public Reply(Long repno, Board bno, String content, String regdate, Member writerId, Reply parentId, String delChk) {
		this.repno = repno;
		this.bno = bno;
		this.content = content;
		this.regdate = regdate;
		this.writerId = writerId;
		this.parentId = parentId;
		this.delChk = delChk;
	}
	
	
}
