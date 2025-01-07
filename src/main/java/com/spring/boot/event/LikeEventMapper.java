package com.spring.boot.event;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.BoardLike;
import com.spring.boot.entity.Member;

public class LikeEventMapper {
	public static LikeEventDto LikeEventDto(LikeAddedEvent event) {
		BoardLike like = event.getLike();
		Board board = event.getBoard(); //좋아요 누른 글
		Member member = event.getMember(); //알림 받을 사용자
		
		return new LikeEventDto(
					like.getLikeId().getUserNo(),
					board.getBno(),
					member
				);
	}
}
