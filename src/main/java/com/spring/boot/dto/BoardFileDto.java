package com.spring.boot.dto;

import java.util.Date;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.BoardFile;
import com.spring.boot.entity.FileInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardFileDto {
	@Getter
	public static class response{
		private String saveName;
		private Date uploadDate;
		public response(BoardFile boardFile) {
			this.saveName = boardFile.getFno().getSaveName();
			this.uploadDate = boardFile.getFno().getRegdate();
		}
		public response(String saveName, Date uploadDate) {
			this.saveName = saveName;
			this.uploadDate = uploadDate;
		}
	}
	
	@Getter
	@NoArgsConstructor
	public static class request{
		public BoardFile toEntity(Board bno, FileInfo fno) {
			return BoardFile.builder()
						.bno(bno)
						.fno(fno)
						.build();
		}
	}
}
