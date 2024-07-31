package com.spring.boot.reply.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.boot.entity.Reply;
import static com.spring.boot.entity.QReply.reply;
import lombok.AllArgsConstructor;
@Repository
@AllArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {
	
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Reply> list(long board) {
		return jpaQueryFactory.selectFrom(reply)
							.leftJoin(reply.parentId()).fetchJoin()
							.where(reply.bno().bno.eq(board))
							.orderBy(reply.parentId().repno.asc().nullsFirst(), reply.regdate.asc())
							.fetch();
	}

}
