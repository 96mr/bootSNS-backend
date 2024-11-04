package com.spring.boot.dto;

import java.util.List;

import javax.validation.constraints.Size;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardDto {
	
	@Getter
	public static class response {
		private Long bno;
		private String content;
		private MemberDto.simple writer;
		private String regdate;
		private int hit;
		private int likesCnt;
		private int replyCnt;
		
		public response(Board board) {
			this.bno = board.getBno();
			this.content = board.getContent();
			this.writer = new MemberDto.simple(board.getWriterId());
			this.regdate = board.getRegdate().toString();
			this.hit = board.getHit();
			this.likesCnt = board.getLikes().size();
			this.replyCnt = board.getReplies().size();
		}
		
		public response(Long bno, String content, Member writer, String regdate, int hit, int likesCnt, int replyCnt) {
			this.bno = bno;
			this.content = content;
			this.writer = new MemberDto.simple(writer);
			this.regdate = regdate;
			this.hit = hit;
			this.likesCnt = likesCnt;
			this.replyCnt = replyCnt;
		}
	}
	
	@NoArgsConstructor
	@Getter
	public static class create{
		@Size(min = 1, max = 300)
		private String content;
		
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
	public static class infoWithImage{
		private Long bno;
		private String content;
		private MemberDto.simple writer;
		private String regdate;
		private int hit;
		private int likesCnt;
		private int replyCnt;
		private List<BoardFileDto.response> files;
		
		public infoWithImage(BoardDto.response board, List<BoardFileDto.response> files) {
			this.bno = board.getBno();
			this.content = board.getContent();
			this.writer = board.getWriter();
			this.regdate = board.getRegdate().toString();
			this.hit = board.getHit();
			this.likesCnt = board.getLikesCnt();
			this.replyCnt = board.getReplyCnt();
			this.files = files;
		}
	}

}
