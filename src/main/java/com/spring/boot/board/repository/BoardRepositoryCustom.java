package com.spring.boot.board.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.spring.boot.dto.BoardDto;

public interface BoardRepositoryCustom {
	Slice<BoardDto.infoWithImage> timeline(long userNo, Pageable pageable);
	Slice<BoardDto.infoWithImage> findAllBoard(long userNo, Pageable pageable);
	Slice<BoardDto.response> textBoard(long userNo, Pageable pageable);
	Slice<BoardDto.infoWithImage> mediaBoard(long userNo, Pageable pageable);
	Slice<BoardDto.infoWithImage> likesBoard(long userNo, Pageable pageable);
}
