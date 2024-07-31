package com.spring.boot.dto;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Like;
import com.spring.boot.entity.LikeId;
import com.spring.boot.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikeDto {
	
	@Getter
	@NoArgsConstructor
	public static class response{
		private long bno;	
		private MemberDto.simple likeId;
		
		public response(Like like) {
			this.bno = like.getBno().getBno();
			this.likeId = new MemberDto.simple(like.getLikeId());
		}
	}
	
	@Getter
	@NoArgsConstructor
	public static class request{
		public Like toEntity(Board bno, Member likeId) {
			LikeId id = new LikeId(bno.getBno(), likeId.getUserNo());
			return Like.builder()
					.id(id)
					.bno(bno)
					.likeId(likeId)
					.build();
		}
	}
}
