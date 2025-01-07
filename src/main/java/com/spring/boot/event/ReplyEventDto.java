package com.spring.boot.event;

import com.spring.boot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyEventDto {
	private long sender;
	private Member receiver;
	private String content;
	private long bno;
}
