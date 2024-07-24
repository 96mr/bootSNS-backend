package com.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.boot.dto.NoticeDto;
import com.spring.boot.entity.Notice;
import com.spring.boot.member.repository.MemberRepository;
import com.spring.boot.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final MemberRepository memberRepository;
	private final NotificationRepository notificationRepository;
	
	public List<NoticeDto.response> noticeList(int userNo){
		List<Notice> result = notificationRepository.findByReceiver(userNo);
		return result.stream().map(NoticeDto.response::new).collect(Collectors.toList());
	}
}
