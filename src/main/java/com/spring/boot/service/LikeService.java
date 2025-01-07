package com.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.spring.boot.board.repository.BoardRepository;
import com.spring.boot.dto.LikeDto;
import com.spring.boot.entity.Board;
import com.spring.boot.entity.BoardLike;
import com.spring.boot.entity.Member;
import com.spring.boot.event.LikeAddedEvent;
import com.spring.boot.like.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final BoardRepository boardRepository;
	private final LikeRepository likeRepository;
	private final ApplicationEventPublisher eventPublisher;
	
	public List<LikeDto.response> listByBoard(long bno){
		Board board = boardRepository.findById(bno)
						.orElseThrow(()->new IllegalStateException("존재하지 않는 게시물"));
		List<BoardLike> likes = likeRepository.findByBno(board);
		return likes.stream().map(LikeDto.response::new).collect(Collectors.toList());
	}
	
	public boolean find(long bno, Member member) {
		return likeRepository.existsByBnoAndLikeId(bno, member.getUserNo());
	}
	
	public void insert(long bno, Member member) {
		Board board = boardRepository.findById(bno)
				.orElseThrow(()->new IllegalStateException("존재하지 않는 게시물"));
		LikeDto.request dto = new LikeDto.request();
		BoardLike like = likeRepository.save(dto.toEntity(board, member));
		
		LikeAddedEvent event = new LikeAddedEvent(like, board, member);
		eventPublisher.publishEvent(event);
	}
	
	public void delete(long bno, Member member) {
		Board board = boardRepository.findById(bno)
				.orElseThrow(()->new IllegalStateException("존재하지 않는 게시물"));
		LikeDto.request dto = new LikeDto.request();
		likeRepository.delete(dto.toEntity(board, member));
	}

}
