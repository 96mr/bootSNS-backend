package com.spring.boot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.ReplyDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReplyController {
	private final ReplyService replyService;
	
	@GetMapping("/reply/{board}")
	public ResponseEntity<List<ReplyDto.response>> list(@PathVariable("board") long board){
		return ResponseEntity.ok().body(replyService.list(board));
	}
	
	@PostMapping("/reply/{board}")
	public ResponseEntity<String> insert(@RequestBody ReplyDto.request dto,
										@AuthenticationPrincipal MemberDetails member){
		try {		
			replyService.save(dto, member.getMember());
			return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 작성되었습니다.");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
		}
	}
}
