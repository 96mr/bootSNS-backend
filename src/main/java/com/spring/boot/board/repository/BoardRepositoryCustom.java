package com.spring.boot.board.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.spring.boot.dto.BoardDto;

public interface BoardRepositoryCustom {
	Slice<BoardDto.info> timeline(long userNo, Pageable pageable);
}
