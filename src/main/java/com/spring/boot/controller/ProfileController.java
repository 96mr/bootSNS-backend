package com.spring.boot.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.dto.BoardDto;
import com.spring.boot.dto.ProfileDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.BoardService;
import com.spring.boot.service.ProfileService;

import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileService profileService;
	private final BoardService boardService;
	
	@GetMapping("/{id}/profile")
	public ResponseEntity<ProfileDto.info> profile(@PathVariable("id") String id){
		return ResponseEntity.ok().body(profileService.getProfile(id));
	}
	
	@GetMapping("/{id}/profile/boards")
	public ResponseEntity<Slice<BoardDto.infoWithImage>> allBoard(
											@PathVariable("id") String id, Pageable pageable){
		return ResponseEntity.ok().body(boardService.getAllBoard(id, pageable));
	}
	
	@GetMapping("/{id}/profile/text")
	public ResponseEntity<Slice<BoardDto.response>> textBoard(
										@PathVariable("id") String id, Pageable pageable){
		return ResponseEntity.ok().body(boardService.getTextBoard(id, pageable));
	}
	
	@GetMapping("/{id}/profile/media")
	public ResponseEntity<Slice<BoardDto.infoWithImage>> mediaBoard(
											@PathVariable("id") String id, Pageable pageable){
		return ResponseEntity.ok().body(boardService.getMediaBoard(id, pageable));
	}
	
	@GetMapping("/{id}/profile/likes")
	public ResponseEntity<Slice<BoardDto.infoWithImage>> likesBoard(
											@PathVariable("id") String id, Pageable pageable){
		return ResponseEntity.ok().body(boardService.getMediaBoard(id, pageable));
	}
	
	@GetMapping("/settings/profile")
	public ResponseEntity<ProfileDto.myProfile> update(@AuthenticationPrincipal MemberDetails memberDetail){
		return ResponseEntity.ok().body(profileService.getMyProfile(memberDetail.getMember()));
	}
	
	@PutMapping("/settings/profile")
	public ResponseEntity<ProfileDto.myProfile> update(@Valid @RequestPart ProfileDto.request dto
								 ,@AuthenticationPrincipal MemberDetails memberDetail
								 ,@RequestPart(required = false) MultipartFile image){
		if (memberDetail == null) {
	        throw new IllegalArgumentException("Member detail is null");
	    }
		return ResponseEntity.ok().body(profileService.updateProfile(memberDetail.getMember(), dto, image));
	}

}
