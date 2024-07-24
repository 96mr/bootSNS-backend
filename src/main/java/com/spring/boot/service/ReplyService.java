package com.spring.boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dto.ReplyDto;
import com.spring.boot.entity.Reply;
import com.spring.boot.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyRepository replyRepository;
	
	@Transactional
	public List<ReplyDto.response> list(int board){
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
	
	public void save(ReplyDto.request reply) {
		replyRepository.save(reply.toEntity());
	}
}
