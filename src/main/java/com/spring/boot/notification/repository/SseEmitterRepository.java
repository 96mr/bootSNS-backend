package com.spring.boot.notification.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SseEmitterRepository{
	private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
	
	public SseEmitter save(long id, SseEmitter emitter) {
		emitters.put(id, emitter);
		return emitter;
	}
	
	public SseEmitter get(long id) {
		return emitters.get(id);
	}
	
	public void delete(long id) {
		emitters.remove(id);
	}
}
