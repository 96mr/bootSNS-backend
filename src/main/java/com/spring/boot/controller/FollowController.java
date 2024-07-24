package com.spring.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.FollowDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {
	private final FollowService followService;
	
	@GetMapping("/{id}/following")
	public ResponseEntity<List<FollowDto.response>> followingList(@PathVariable String id){
		return ResponseEntity.ok().body(followService.followingList(id));
	}
	
	@GetMapping("/{id}/follower")
	public ResponseEntity<List<FollowDto.response>> followerList(@PathVariable String id){
		return ResponseEntity.ok().body(followService.followerList(id));
	}
	
	//팔로우 여부
	@GetMapping("/{id}/follow")
	public ResponseEntity<Boolean> followGet(@AuthenticationPrincipal MemberDetails memberDetail, 
											@PathVariable String target){
		return ResponseEntity.ok().body(followService.found(memberDetail.getMember(), target));
	}
	
	//팔로우 추가
	@PostMapping("/{id}/follow")
	public ResponseEntity<?> insert(@AuthenticationPrincipal MemberDetails memberDetail, 
									@PathVariable String target){
		followService.insert(memberDetail.getMember(), target);
		return ResponseEntity.ok().body("");
	}
	
	//팔로우 삭제
	@DeleteMapping("/{id}/follow")
	public ResponseEntity<?> delete(@AuthenticationPrincipal MemberDetails memberDetail, 
									@PathVariable String target){
		followService.delete(memberDetail.getMember(), target);
		return ResponseEntity.ok().body("");
	}
}
