package com.spring.boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.board.repository.BoardRepository;
import com.spring.boot.dto.ReplyDto;
import com.spring.boot.entity.Board;
import com.spring.boot.entity.Member;
import com.spring.boot.entity.Reply;
import com.spring.boot.event.ReplyAddedEvent;
import com.spring.boot.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyRepository replyRepository;
	private final BoardRepository boardRepository;
	private final ApplicationEventPublisher eventPublisher;
	
	@Transactional
	public List<ReplyDto.response> list(long board){
		List<Reply> list = replyRepository.list(board);
		List<ReplyDto.response> result = new ArrayList<>();
		Map<Long, ReplyDto.response> map = new HashMap<>();
		
		list.stream().forEach( r -> {
					ReplyDto.response res = new ReplyDto.response(r);
					if(res.getParentId() != null)
						map.get(r.getParentId().getRepno()).getChildren().add(res); //children에 추가
					else
						result.add(res);
					map.put(res.getRepno(), res);
		});
		return result;
	}
	
	public void save(ReplyDto.request request, Member member) {
		Board board = boardRepository.findById(request.getBno())
				.orElseThrow(()-> new IllegalStateException("존재하지 않는 게시글입니다."));
		Reply reply = replyRepository.save(request.toEntity(board, member));
		
		ReplyAddedEvent event = new ReplyAddedEvent(reply, member);
		eventPublisher.publishEvent(event);
	}
}
