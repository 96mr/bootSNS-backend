package com.spring.boot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.spring.boot.dto.NoticeDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.NotificationService;
import com.spring.boot.service.SseEmitterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {
	
	private final NotificationService notificationService;
	private final SseEmitterService sseEmitterService;
	
	//알림 리스트
	@GetMapping("/notification")
	public ResponseEntity<List<NoticeDto.response>> getNotification(
									@AuthenticationPrincipal MemberDetails memberDetail){
		if (memberDetail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
		List<NoticeDto.response> result = notificationService.noticeList(memberDetail.getMember());
		return ResponseEntity.ok().body(result);
	}
	
	// SSE 연결
	@GetMapping(value = "/notification/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> subscribe(@AuthenticationPrincipal MemberDetails memberDetail){
		if(memberDetail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
		try {
	        SseEmitter sseEmitter = sseEmitterService.subscribe(memberDetail.getMember()); 
	        if(sseEmitter == null) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
	        }
	        
	        return ResponseEntity.ok().body(sseEmitter);
	    }catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}

    // 알림 읽기 처리
    @PostMapping("/notifications/{userNo}/mark-read")
    public ResponseEntity<?> markNotificationAsRead(@PathVariable Long userNo) {
        notificationService.markNotificationAsRead(userNo);
        return ResponseEntity.ok().build();
    }
}
