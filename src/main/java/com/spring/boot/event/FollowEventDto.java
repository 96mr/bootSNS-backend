package com.spring.boot.event;

import java.util.Date;

import com.spring.boot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowEventDto {
	private long sender;  // 팔로우한 사람의 ID
    private Member receiver;
    private long followedId;  // 팔로우된 사람의 ID
    private Date regdate;
}
