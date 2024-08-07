package com.spring.boot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
@Getter
public class FollowId implements Serializable{
	@Column(name="m_id")
	private long mid;
	@Column(name="t_id")
	private long tid;
	
	public FollowId(long mid, long tid) {
        this.mid = mid;
        this.tid = tid;
    }
}
