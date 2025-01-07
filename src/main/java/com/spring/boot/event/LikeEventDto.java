package com.spring.boot.event;

import com.spring.boot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeEventDto {
	private long boardId;
	private long sender;
	private Member receiver;
}
