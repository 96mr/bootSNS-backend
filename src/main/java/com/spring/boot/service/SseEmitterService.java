package com.spring.boot.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.spring.boot.entity.Member;

@Service
public class SseEmitterService {
	private NotificationService notificationService;
	private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();	
	private final Long DEFAULT_TIMEOUT = 60L * 1000 * 5;
	
	public SseEmitterService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
	
	// 새로운 SSE 구독 요청 처리
    public SseEmitter subscribe(Member receiver) {
    	long userId = receiver.getUserNo();
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitters.put(userId, emitter);

        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));
       
        int notificationCount = notificationService.countUnreadNotification(receiver);
      
        try {
			emitter.send(SseEmitter.event()
			        .name("notificationCount")
			        .data(notificationCount));
		} catch (IOException e) {
			e.printStackTrace();
			emitters.remove(userId);
		}
        return emitter;
    }

    // 알림 전송
    public void sendNotification(long userId, int notificationCount) {
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        		.name("notificationCount")
                        		.data(notificationCount));
            } catch (IOException e) {
                emitters.remove(userId); // 전송 실패 시 해당 emitter 제거
            }
        }
    }

    public Map<Long, SseEmitter> getEmitters() {
        return emitters;
    }
}
