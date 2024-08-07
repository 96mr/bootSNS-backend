package com.spring.boot.board.repository;

import static com.spring.boot.entity.QBoard.board;
import static com.spring.boot.entity.QBoardFile.boardFile;
import static com.spring.boot.entity.QFileInfo.fileInfo;
import static com.spring.boot.entity.QFollow.follow;
import static com.spring.boot.entity.QMember.member;
import static com.spring.boot.entity.QProfile.profile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.boot.dto.BoardDto;
import com.spring.boot.dto.BoardFileDto;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Slice<BoardDto.info> timeline(long userNo, Pageable pageable) {	
		Date now = new Date();

		List<BoardDto.response> boards = jpaQueryFactory.select(
					Projections.constructor(
						BoardDto.response.class, 
						board.bno,
						board.content,
						board.writerId().as("writer"),
						new CaseBuilder()
							.when(board.regdate.between(new Date(now.getTime() - 60*1000), now))
	                        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(SECOND FROM (SYSDATE - {0})))", board.regdate).concat("초전"))
	                        .when(board.regdate.between(new Date(now.getTime() - 3600*1000), now))
	                        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(MINUTE FROM (SYSDATE - {0})))", board.regdate).concat("분전"))
	                        .when(board.regdate.between(new Date(now.getTime() - 24*3600*1000), now))
	                        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(HOUR FROM (SYSDATE - {0})))", board.regdate).concat("시간전"))
	                        .when(board.regdate.between(new Date(now.getTime() - 7*24*3600*1000), now))
	                        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(DAY FROM (SYSDATE - {0})))", board.regdate).concat("일전"))
	                        .otherwise(Expressions.stringTemplate("TO_CHAR({0}, 'YYYY\"년 \"MM\"월 \"DD\"일')", board.regdate))
                        .as("regdate"),
						board.hit,
						board.likes.size().as("likesCnt"),
						board.replies.size().as("replyCnt")
					)
				)
				.from(board)
				.innerJoin(board.writerId(), member)
				.innerJoin(board.writerId().profile(), profile)
				.fetchJoin()
				.where(
						board.writerId().userNo.eq(userNo)
						.or(
							board.writerId().userNo.in(
								JPAExpressions
									.select(follow.tid().userNo)
									.from(follow)
									.where(follow.mid().userNo.eq(userNo))
							)
						)
						.and(board.delChk.eq("N"))
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(board.regdate.desc())
				.fetch();
		
		List<BoardDto.info> list = boards
						.stream()
						.map(boardDto -> {
							List<BoardFileDto.response> files = jpaQueryFactory.select(
										Projections.constructor(
												BoardFileDto.response.class, 
												boardFile.fno().saveName.as("saveName"),
												boardFile.fno().regdate.as("uploadDate")
										)
									)
									.from(boardFile)
									.leftJoin(boardFile.fno(), fileInfo)
									.where(boardFile.bno().bno.eq(boardDto.getBno()))
									.fetch();
							
							return new BoardDto.info(boardDto, files);
						}).collect(Collectors.toList());
		return new SliceImpl<>(list, pageable, hasNext(list, pageable.getPageSize()));
	}
	
	public boolean hasNext(List<BoardDto.info> list, int size) {
		if(list.size() > size) {
			list.remove(size);
			return true;
		}
		return false;
	}
	
}
