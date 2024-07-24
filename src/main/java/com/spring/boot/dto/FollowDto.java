package com.spring.boot.dto;

import java.util.Date;

import com.spring.boot.entity.Follow;
import com.spring.boot.entity.FollowId;
import com.spring.boot.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class FollowDto {
	
	@Getter
	public static class response {
		private MemberDto.simple mid;
		private MemberDto.simple tid;
		
		public response(Follow follow) {
			this.mid = new MemberDto.simple(follow.getMid());
			this.tid = new MemberDto.simple(follow.getTid());
		}
	}
	
	@Getter
	@NoArgsConstructor
	public static class request {
		private Date regdate;
	
		public Follow toEntity(Member mid, Member tid) {
			FollowId followId = new FollowId(mid.getUserNo(), tid.getUserNo());
			return Follow.builder()
					.id(followId)
					.mid(mid)
					.tid(tid)
					.regdate(regdate)
					.build();
		}
		
	}
}
