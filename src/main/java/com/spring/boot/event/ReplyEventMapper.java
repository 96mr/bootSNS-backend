package com.spring.boot.event;

import com.spring.boot.entity.Member;
import com.spring.boot.entity.Reply;

public class ReplyEventMapper {
	public static ReplyEventDto ReplyEventDto(ReplyAddedEvent event) {
        Reply reply = event.getReply();
    	Member member = event.getMember();
    	
        // DTO 생성
        return new ReplyEventDto(
                reply.getWriterId().getUserNo(),
                member,
                reply.getContent(),
                reply.getBno().getBno()
        );
    }
}
