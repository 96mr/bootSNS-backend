package com.spring.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.NoticeDto;
import com.spring.boot.entity.Member;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {
	
	private final NotificationService notificationService;
	
	@GetMapping("/notification")
	public ResponseEntity<List<NoticeDto.response>> getNotification(
									@AuthenticationPrincipal MemberDetails memberDetail){
		Member member = memberDetail.getMember();
		List<NoticeDto.response> result = notificationService.noticeList(member.getUserNo());
		return ResponseEntity.ok().body(result);
	}
}
