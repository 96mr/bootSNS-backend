package com.spring.boot.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.spring.boot.service.NotificationService;
import com.spring.boot.service.SseEmitterService;

@Component
public class NotificationEventListener {
	private final NotificationService notificationService;
	private final SseEmitterService sseEmitterService;
	
	public NotificationEventListener(NotificationService notificationService, SseEmitterService sseEmitterService) {
        this.notificationService = notificationService;
        this.sseEmitterService = sseEmitterService;
    }
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleReplyAddedEvent(ReplyAddedEvent event) {
		ReplyEventDto eventDto = ReplyEventMapper.ReplyEventDto(event);
		notificationService.sendReplyNotification(eventDto);
		int unreadCount = notificationService.countUnreadNotification(eventDto.getReceiver());
		sseEmitterService.sendNotification(eventDto.getReceiver().getUserNo(), unreadCount);
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleLikeAddedEvent(LikeAddedEvent event) {
		LikeEventDto eventDto = LikeEventMapper.LikeEventDto(event);
		notificationService.sendLikeNotification(eventDto);
		int unreadCount = notificationService.countUnreadNotification(eventDto.getReceiver());
		sseEmitterService.sendNotification(eventDto.getReceiver().getUserNo(), unreadCount);
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleFollowAddedEvent(FollowAddedEvent event) {
		FollowEventDto eventDto = FollowEventMapper.FollowEventDto(event);
		notificationService.sendFollowNotification(eventDto);
		int unreadCount = notificationService.countUnreadNotification(eventDto.getReceiver());
		sseEmitterService.sendNotification(eventDto.getFollowedId(), unreadCount);
	}
	
}
