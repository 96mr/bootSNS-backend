package com.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.boot.board.repository.BoardRepository;
import com.spring.boot.dto.LikeDto;
import com.spring.boot.entity.Board;
import com.spring.boot.entity.Like;
import com.spring.boot.entity.Member;
import com.spring.boot.like.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final BoardRepository boardRepository;
	private final LikeRepository likeRepository;
	
	public List<LikeDto.response> listByBoard(int bno){
		Board board = boardRepository.findById(bno)
						.orElseThrow(()->new IllegalStateException("존재하지 않는 게시물"));
		List<Like> likes = likeRepository.findByBno(board);
		return likes.stream().map(LikeDto.response::new).collect(Collectors.toList());
	}
	
	public void insert(int bno, Member member) {
		Board board = boardRepository.findById(bno)
				.orElseThrow(()->new IllegalStateException("존재하지 않는 게시물"));
		LikeDto.request dto = LikeDto.request.builder()
								.bno(board)
								.build();
		likeRepository.save(dto.toEntity(member));
	}
	
	public void delete(int bno, Member member) {
		Board board = boardRepository.findById(bno)
				.orElseThrow(()->new IllegalStateException("존재하지 않는 게시물"));
		LikeDto.request dto = LikeDto.request.builder()
									.bno(board)
									.build();
		likeRepository.delete(dto.toEntity(member));
	}
}
