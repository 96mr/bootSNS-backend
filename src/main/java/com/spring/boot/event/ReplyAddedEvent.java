package com.spring.boot.event;

import com.spring.boot.entity.Member;
import com.spring.boot.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyAddedEvent {
	private Reply reply;
	private Member member;//알림 받을 사용자
}
