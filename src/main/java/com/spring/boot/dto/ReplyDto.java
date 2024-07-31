package com.spring.boot.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Member;
import com.spring.boot.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReplyDto {
	@Getter
	public static class response{
		private Long repno;
		private String content; //500
		private String regdate;
		private MemberDto.simple writerId; //댓글 작성자
		private Long parentId;
		private List<ReplyDto.response> children = new ArrayList<>();
		private String delChk;
	
		public response(Reply reply) {
			this.repno = reply.getRepno();
			this.content = reply.getDelChk() == "Y" ? "삭제된 댓글입니다.": reply.getContent();
			this.regdate = reply.getRegdate();
			this.writerId = new MemberDto.simple(reply.getWriterId());
			this.parentId = reply.getParentId() == null ? null : reply.getParentId().getRepno();
			this.delChk = reply.getDelChk();
		}
	}
	
	@AllArgsConstructor
	@Getter
	@Builder
	public static class request{
		private Long repno;
		private long bno;
		@NotBlank
		private String content; //500
		private String regdate;
		private Reply parentId;
		private String delChk;
		
		public Reply toEntity(Board board, Member writerId) {
			return Reply.builder()
					.repno(repno)
					.bno(board)
					.content(content)
					.regdate(regdate)
					.writerId(writerId)
					.parentId(parentId)
					.delChk(delChk)
					.build();
		}
	}
}
