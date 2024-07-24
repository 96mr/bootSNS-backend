package com.spring.boot.dto;

import java.util.ArrayList;
import java.util.List;

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
	
		public response(Reply reply) {
			this.repno = reply.getRepno();
			this.content = reply.getDelChk() == "Y" ? "삭제된 댓글입니다.": reply.getContent();
			this.regdate = reply.getRegdate();
			this.writerId = new MemberDto.simple(reply.getWriterId());
			this.parentId = reply.getParentId() == null ? null : reply.getParentId().getRepno();
		}
	}
	
	@AllArgsConstructor
	@Getter
	@Builder
	public static class request{
		private Long repno;
		private Board bno;
		private String content; //500
		private String regdate;
		private Member writerId;
		private Reply parentId;
		private String delChk;
		
		public Reply toEntity() {
			return Reply.builder()
					.repno(repno)
					.bno(bno)
					.content(content)
					.regdate(regdate)
					.writerId(writerId)
					.parentId(parentId)
					.delChk(delChk)
					.build();
		}
	}
	/*
	@Getter
	public static class info {
		private int repno;
		private int bno;
		private String content; //500
		private String regdate;
		private String writerName;
		private String writer; //댓글 작성자
		private ReplyDto.response parentId;
		private String delChk;
		
		public info(Reply reply) {
			this.repno = reply.getRepno();
			this.bno = reply.getBno().getBno();
			this.content = reply.getContent();
			this.regdate = reply.getRegdate();
			this.writerName = reply.getWriterId().getProfile().getName();
			this.writer = reply.getWriterId().getId();
			this.parentId = new ReplyDto.response(reply.getParentId());
			this.delChk = reply.getDelChk();
		}
	}*/
}
