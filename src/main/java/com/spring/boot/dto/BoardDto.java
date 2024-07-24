package com.spring.boot.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.boot.entity.Board;
import com.spring.boot.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardDto {
	
	@Getter
	public static class response {
		private int bno;
		private String content;//1000
		private int writerId; //fk : member의 user_id
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private Date regdate;
		private int hit;
		private String delChk;
		
		public response(Board board) {
			this.bno = board.getBno();
			this.content = board.getContent();
			this.writerId = board.getWriterId().getUserNo();
			this.regdate = board.getRegdate();
			this.hit = board.getHit();
			this.delChk = board.getDelChk();
		}
	}
	
	@NoArgsConstructor
	@Getter
	public static class create{
		private String content;//1000
		
		@Builder
		public create(String content, Member writer) {
			this.content = content;
		}
		
		public Board toEntity(Member writer) {
			return Board.builder()
						.content(content)
						.writerId(writer)
						.build();
		}
	}
	
	@Getter
	@NoArgsConstructor
	public static class info{
		private int bno;
		private String content;//1000
		private MemberDto.simple writer;
		private String regdate;
		private int hit;
		private int likesCnt;
		private int replyCnt;
		
		public info(Board board) {
			this.bno = board.getBno();
			this.content = board.getContent();
			this.writer = new MemberDto.simple(board.getWriterId());
			this.regdate = board.getRegdate().toString();
			this.hit = board.getHit();
			this.likesCnt = board.getLikes().size();
			this.replyCnt = board.getReplies().size();
		}
		
		public info(int bno, String content, Member writer, String regdate, int hit, int likesCnt, int replyCnt) {
			this.bno = bno;
			this.content = content;
			this.writer = new MemberDto.simple(writer);
			this.regdate = regdate;
			this.hit = hit;
			this.likesCnt = likesCnt;
			this.replyCnt = replyCnt;
		}
	}

}
