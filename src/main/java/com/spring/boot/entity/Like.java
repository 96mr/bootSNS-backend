package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
	@EmbeddedId
	private LikeId id;
	@MapsId("bno")
	@ManyToOne
	@JoinColumn(name="bno", insertable = false, updatable = false)
	private Board bno;	
	@MapsId("like_id")
	@ManyToOne
	@JoinColumn(name = "like_id", insertable = false, updatable = false)
	private Member likeId;
	private Date regdate;
	
	@Builder
	public Like(LikeId id, Board bno, Member likeId, Date regdate) {
		this.id = id;
		this.bno = bno;
		this.likeId = likeId;
		this.regdate = regdate;
	}
}
