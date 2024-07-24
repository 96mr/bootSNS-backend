package com.spring.boot.dto;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.BoardFile;
import com.spring.boot.entity.FileInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardFileDto {
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
