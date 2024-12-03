package com.spring.boot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.LikeDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {
	private final LikeService likeService;
	
	@GetMapping("/{id}/board/{board}/likes")
	public ResponseEntity<List<LikeDto.response>> listByBoard(@PathVariable("board") long board){
		return ResponseEntity.ok().body(likeService.listByBoard(board));
	}
	
	@GetMapping("/like/{board}")
	public ResponseEntity<Boolean> find(@PathVariable("board") long board,
								@AuthenticationPrincipal MemberDetails memberDetail){
		return ResponseEntity.ok(likeService.find(board, memberDetail.getMember()));
	}
	
	@PostMapping("/like/{board}")
	public ResponseEntity<?> insert(@PathVariable("board") long board,
								@AuthenticationPrincipal MemberDetails memberDetail){
		likeService.insert(board, memberDetail.getMember());
		return ResponseEntity.created(URI.create("/home")).build();
	}
	
	@DeleteMapping("/like/{board}")
	public ResponseEntity<?> delete(@PathVariable("board") long board,
							@AuthenticationPrincipal MemberDetails memberDetail){
		likeService.delete(board, memberDetail.getMember());
		return ResponseEntity.ok().body("");
	}
}
