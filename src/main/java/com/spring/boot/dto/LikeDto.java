package com.spring.boot.dto;

import java.util.Date;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Like;
import com.spring.boot.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikeDto {
	
	@Getter
	@NoArgsConstructor
	public static class response{
		private String rowId;
		private int bno;	
		private MemberDto.simple likeId;
		private Date regdate;
		
		public response(Like like) {
			this.bno = like.getBno().getBno();
			this.likeId = new MemberDto.simple(like.getLikeId());
		}
	}
	
	@Getter
	@NoArgsConstructor
	public static class request{
		private Board bno;
		
		@Builder
		public request(Board bno) {
			this.bno = bno;
		}
		
		public Like toEntity(Member likeId) {
			return Like.builder()
					.bno(bno)
					.likeId(likeId)
					.build();
		}
	}
}
