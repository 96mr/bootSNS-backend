package com.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dto.NoticeDto;
import com.spring.boot.entity.Member;
import com.spring.boot.entity.Notice;
import com.spring.boot.entity.NotificationType;
import com.spring.boot.event.FollowEventDto;
import com.spring.boot.event.LikeEventDto;
import com.spring.boot.event.ReplyEventDto;
import com.spring.boot.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
	
	public List<NoticeDto.response> noticeList(Member member){
		List<Notice> result = notificationRepository.findByReceiver(member);
		return result.stream().map(NoticeDto.response::new).collect(Collectors.toList());
	}

	public void sendReplyNotification(ReplyEventDto dto) {
		NoticeDto.request request = NoticeDto.request.builder()
													.sender(dto.getSender())
													.receiver(dto.getReceiver())
													.notetype(NotificationType.REPLY)
													.content(dto.getContent())
													.bno(dto.getBno())
													.build();
		notificationRepository.save(request.toEntity());
	}
	
	public void sendLikeNotification(LikeEventDto dto) {
		NoticeDto.request request = NoticeDto.request.builder()
													.sender(dto.getSender())
													.receiver(dto.getReceiver())
													.notetype(NotificationType.LIKE)
													.bno(dto.getBoardId())
													.build();
		notificationRepository.save(request.toEntity());
	}
	
	public void sendFollowNotification(FollowEventDto dto) {
		NoticeDto.request request = NoticeDto.request.builder()
													.sender(dto.getSender())
													.receiver(dto.getReceiver())
													.notetype(NotificationType.FOLLOW)		
													.build();
		notificationRepository.save(request.toEntity());
	}

	public int countUnreadNotification(Member receiver) {
		System.out.println("countUnreadNotification :"+receiver.getUserNo());
		return notificationRepository.countByReceiverAndChkdateIsNull(receiver);
	}
	
	@Transactional
	public void markNotificationAsRead(long userNo){
		notificationRepository.marksAllRead(userNo);
	}
}
