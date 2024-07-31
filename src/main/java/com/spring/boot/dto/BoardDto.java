package com.spring.boot.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
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
		private int writerId;
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
	public static class info{
		private Long bno;
		private String content;
		private MemberDto.simple writer;
		private String regdate;
		private int hit;
		private int likesCnt;
		private int replyCnt;
		private List<BoardFileDto.response> files;
		
		public info(Board board) {
			this.bno = board.getBno();
			this.content = board.getContent();
			this.writer = new MemberDto.simple(board.getWriterId());
			this.regdate = board.getRegdate().toString();
			this.hit = board.getHit();
			this.likesCnt = board.getLikes().size();
			this.replyCnt = board.getReplies().size();
			this.files = board.getFiles().stream()
										.map(BoardFileDto.response::new)
										.collect(Collectors.toList());
		}
		
		public info(Long bno, String content, Member writer, String regdate, int hit, int likesCnt, int replyCnt, List<BoardFileDto.response> files) {
			this.bno = bno;
			this.content = content;
			this.writer = new MemberDto.simple(writer);
			this.regdate = regdate;
			this.hit = hit;
			this.likesCnt = likesCnt;
			this.replyCnt = replyCnt;
			this.files = files;
		}
	}

}
