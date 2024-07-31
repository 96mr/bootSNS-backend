package com.spring.boot.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
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

	@GetMapping({"/home","/","/index"})
	public ResponseEntity<Slice<BoardDto.info>> timeline(Pageable pageable,
										@AuthenticationPrincipal MemberDetails memberDetail){
		return ResponseEntity.ok().body(boardService.timeline(memberDetail.getMember(), pageable));
	}
	
	@GetMapping("/{id}/board/{board}")
	public ResponseEntity<BoardDto.info> selectBoard(@PathVariable("id") String id, 
													@PathVariable("board") int board){
		return new ResponseEntity<BoardDto.info>(boardService.selectBoard(id, board), HttpStatus.OK);
	}
	
	@PostMapping(value = "/board", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> insertBoard(@RequestPart @Valid BoardDto.create dto, 
										@RequestPart(required=false) MultipartFile[] files,
										@AuthenticationPrincipal MemberDetails memberDetail){
		//내용 또는 파일 여부
		if(dto.getContent().isEmpty() && (files == null || files.length == 0)) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("내용 또는 이미지가 없습니다.");
		if(dto.getContent().length() > 300)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글자수 초과");
		
		//파일 유효성 검사(크기, 타입 등)
		if (files != null) {
	        for (MultipartFile file : files) {
	            if (file.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일이 비었습니다.");
	            }
	           
	            if (file.getSize() > 10 * 1024 * 1024) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일 크기는 최대 10MB까지 가능합니다.");
	            }

	            String contentType = file.getContentType();
	            if (contentType != null && !contentType.startsWith("image")) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미지 파일만 업로드 가능합니다.");
	            }
	        }
	    }

		
		try {
			boardService.save(dto, files, memberDetail.getMember());
			return ResponseEntity.ok().body("게시글이 저장되었습니다.");
		}catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
	    }
	}
	
	@PutMapping("/{id}/board/{board}")
	public ResponseEntity<ResponseBody> deleteBoard(@PathVariable("id") String id, 
													@PathVariable("board") int board){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
