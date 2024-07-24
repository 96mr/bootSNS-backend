package com.spring.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.dto.ProfileDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.ProfileService;

import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileService profileService;
	
	@GetMapping("/{id}/profile")
	public ResponseEntity<ProfileDto.info> profile(@PathVariable("id") String id){
		return ResponseEntity.ok().body(profileService.getProfile(id));
	}
	
	@GetMapping("/settings/profile")
	public ResponseEntity<ProfileDto.info> update(@AuthenticationPrincipal MemberDetails memberDetail){
		return ResponseEntity.ok().body(profileService.getProfile(memberDetail.getMember().getId()));
	}
	
	@PutMapping("/settings/profile")
	public ResponseEntity<ProfileDto.info> update(@RequestBody ProfileDto.request dto
								 ,@AuthenticationPrincipal MemberDetails memberDetail){
		return ResponseEntity.ok().body(profileService.update(memberDetail.getMember(), dto));
	}
}
