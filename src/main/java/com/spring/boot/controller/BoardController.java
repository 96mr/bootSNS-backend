package com.spring.boot.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.dto.BoardDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping({"/home","timeline","/","/index"})
	public ResponseEntity<Slice<BoardDto.info>> timeline(Pageable pageable,
										@AuthenticationPrincipal MemberDetails memberDetail){
		System.out.println("home");
		return ResponseEntity.ok().body(boardService.timeline(memberDetail.getMember(), pageable));
	}
	
	@GetMapping("/{id}/board/{board}")
	public ResponseEntity<BoardDto.info> selectBoard(@PathVariable("id") String id, 
													@PathVariable("board") int board){
		return new ResponseEntity<BoardDto.info>(boardService.selectBoard(id, board), HttpStatus.OK);
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> insertBoard(BoardDto.create dto, MultipartFile[] files,
										@AuthenticationPrincipal MemberDetails memberDetail){
		boardService.save(dto, files, memberDetail.getMember());
		return ResponseEntity.ok().body("");
	}
	
	@PutMapping("/{id}/board/{board}")
	public ResponseEntity<ResponseBody> deleteBoard(@PathVariable("id") String id, 
													@PathVariable("board") int board){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
