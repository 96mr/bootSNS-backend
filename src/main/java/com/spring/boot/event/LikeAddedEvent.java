package com.spring.boot.event;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.BoardLike;
import com.spring.boot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeAddedEvent {
	private BoardLike like;
	private Board board; //좋아요 누른 글
	private Member member; //알림 받을 사용자
}
