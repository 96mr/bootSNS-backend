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
public class LikeId implements Serializable {
	@Column(name="bno")
	private long bno;
	@Column(name="like_id")
	private int likeId;
	
	public LikeId(long bno, int likeId) {
        this.bno = bno;
        this.likeId = likeId;
    }
}
