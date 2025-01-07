package com.spring.boot.event;

import com.spring.boot.entity.Follow;
import com.spring.boot.entity.Member;

public class FollowEventMapper {
	public static FollowEventDto FollowEventDto(FollowAddedEvent event) {
		Follow follow = event.getFollow();     // Follow 엔티티
        Member follower = event.getFollower();  // 팔로우한 사람
        Member followed = event.getFollowed();  // 팔로우된 사람

        // DTO 생성
        return new FollowEventDto(
                follower.getUserNo(),             
                followed,
                followed.getUserNo(),        
                follow.getRegdate()  
        );
    }
}
