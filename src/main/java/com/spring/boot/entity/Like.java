package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="BOARD_LIKE", schema="EX1")
public class Like {
	@Id
	@Column(name = "ROWID")
	private String rowId;
	@ManyToOne
	@JoinColumn(name="bno")
	private Board bno;	
	@ManyToOne
	@JoinColumn(name = "like_id")
	private Member likeId;
	private Date regdate;
	
	@Builder
	public Like(Board bno, Member likeId, Date regdate) {
		this.bno = bno;
		this.likeId = likeId;
		this.regdate = regdate;
	}
}
