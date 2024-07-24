package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="FOLLOW", schema="EX1")
public class Follow{
	@EmbeddedId
	private FollowId id;
	@MapsId("m_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="m_id", insertable = false, updatable = false)
	private Member mid;
	@MapsId("t_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="t_id", insertable = false, updatable = false)
	private Member tid;
	private Date regdate;
	
	@Builder
	public Follow(FollowId id, Member mid, Member tid, Date regdate) {
		this.id = id;
		this.mid = mid;
		this.tid = tid;
		this.regdate = regdate;
	}
}
