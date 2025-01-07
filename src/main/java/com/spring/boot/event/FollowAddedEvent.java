package com.spring.boot.event;

import com.spring.boot.entity.Follow;
import com.spring.boot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowAddedEvent {
	private Follow follow;
	private Member follower; //팔로우 하는 사람
	private Member followed; //팔로우 되는 사람
}
