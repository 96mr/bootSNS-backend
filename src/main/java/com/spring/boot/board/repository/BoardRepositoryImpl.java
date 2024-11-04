package com.spring.boot.board.repository;

import static com.spring.boot.entity.QBoard.board;
import static com.spring.boot.entity.QBoardFile.boardFile;
import static com.spring.boot.entity.QFileInfo.fileInfo;
import static com.spring.boot.entity.QFollow.follow;
import static com.spring.boot.entity.QMember.member;
import static com.spring.boot.entity.QProfile.profile;
import static com.spring.boot.entity.QBoardLike.boardLike;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
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
	public Slice<BoardDto.infoWithImage> timeline(long userNo, Pageable pageable) {	
		Date now = new Date();

		List<BoardDto.response> boards = jpaQueryFactory.select(
					Projections.constructor(
						BoardDto.response.class, 
						board.bno,
						board.content,
						board.writerId().as("writer"),
						formatRegdate(board.regdate, now).as("regdate"),
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
		
		List<BoardDto.infoWithImage> list = boards.stream()
			    .map(boardDto -> {
			        List<BoardFileDto.response> files = getBoardFiles(boardDto.getBno());
			        return new BoardDto.infoWithImage(boardDto, files);
			    })
			    .collect(Collectors.toList());
		return new SliceImpl<>(list, pageable, hasNextImage(list, pageable.getPageSize()));
	}
	
	@Override
	public Slice<BoardDto.infoWithImage> findAllBoard(long userNo, Pageable pageable) {	
		Date now = new Date();

		List<BoardDto.response> boards = jpaQueryFactory.select(
					Projections.constructor(
						BoardDto.response.class, 
						board.bno,
						board.content,
						board.writerId().as("writer"),
						formatRegdate(board.regdate, now).as("regdate"),
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
						.and(board.delChk.eq("N"))
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(board.regdate.desc())
				.fetch();
		
		List<BoardDto.infoWithImage> list = boards.stream()
			    .map(boardDto -> {
			        List<BoardFileDto.response> files = getBoardFiles(boardDto.getBno());
			        return new BoardDto.infoWithImage(boardDto, files);
			    })
			    .collect(Collectors.toList());
		return new SliceImpl<>(list, pageable, hasNextImage(list, pageable.getPageSize()));
	}
	
	@Override
	public Slice<BoardDto.response> textBoard(long userNo, Pageable pageable) {	
		Date now = new Date();

		List<BoardDto.response> boards = jpaQueryFactory.select(
					Projections.constructor(
						BoardDto.response.class, 
						board.bno,
						board.content,
						board.writerId().as("writer"),
						formatRegdate(board.regdate, now).as("regdate"),
						board.hit,
						board.likes.size().as("likesCnt"),
						board.replies.size().as("replyCnt")
					)
				)
				.from(board)
				.innerJoin(board.writerId(), member)
				.innerJoin(board.writerId().profile(), profile)
				.fetchJoin()
				.where(withImageCondition(userNo, "text"))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(board.regdate.desc())
				.fetch();
		return new SliceImpl<>(boards, pageable, hasNextOutImage(boards, pageable.getPageSize()));
	}
	
	@Override
	public Slice<BoardDto.infoWithImage> mediaBoard(long userNo, Pageable pageable) {	
		Date now = new Date();

		List<BoardDto.response> boards = jpaQueryFactory.select(
					Projections.constructor(
						BoardDto.response.class, 
						board.bno,
						board.content,
						board.writerId().as("writer"),
						formatRegdate(board.regdate, now).as("regdate"),
						board.hit,
						board.likes.size().as("likesCnt"),
						board.replies.size().as("replyCnt")
					)
				)
				.from(board)
				.innerJoin(board.writerId(), member)
				.innerJoin(board.writerId().profile(), profile)
				.fetchJoin()
				.where(withImageCondition(userNo, "media"))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(board.regdate.desc())
				.fetch();
		
		List<BoardDto.infoWithImage> list = boards.stream()
			    .map(boardDto -> {
			        List<BoardFileDto.response> files = getBoardFiles(boardDto.getBno());
			        return new BoardDto.infoWithImage(boardDto, files);
			    })
			    .collect(Collectors.toList());
		return new SliceImpl<>(list, pageable, hasNextImage(list, pageable.getPageSize()));
	}
	
	@Override
	public Slice<BoardDto.infoWithImage> likesBoard(long userNo, Pageable pageable) {
	    // 메서드 구현 로직
		Date now = new Date();

		List<BoardDto.response> boards = jpaQueryFactory.select(
					Projections.constructor(
						BoardDto.response.class, 
						board.bno,
						board.content,
						board.writerId().as("writer"),
						formatRegdate(board.regdate, now).as("regdate"),
						board.hit,
						board.likes.size().as("likesCnt"),
						board.replies.size().as("replyCnt")
					)
				)
				.from(board)
				.innerJoin(board.writerId(), member)
				.innerJoin(board.writerId().profile(), profile)
				.innerJoin(boardLike).on(board.bno.eq(boardLike.bno().bno))
				.fetchJoin()
				.where(
						boardLike.id().likeId.eq(userNo)
						.and(board.delChk.eq("N"))
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(board.regdate.desc())
				.fetch();
		
		List<BoardDto.infoWithImage> list = boards.stream()
			    .map(boardDto -> {
			        List<BoardFileDto.response> files = getBoardFiles(boardDto.getBno());
			        return new BoardDto.infoWithImage(boardDto, files);
			    })
			    .collect(Collectors.toList());
		return new SliceImpl<>(list, pageable, hasNextImage(list, pageable.getPageSize()));
	}
	
	private StringExpression formatRegdate(DateExpression<Date> regdateExpression, Date now) {
	    return new CaseBuilder()
	        .when(regdateExpression.between(new Date(now.getTime() - 60 * 1000), now))
	        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(SECOND FROM (SYSDATE - {0})))", regdateExpression).concat("초전"))
	        .when(regdateExpression.between(new Date(now.getTime() - 3600 * 1000), now))
	        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(MINUTE FROM (SYSDATE - {0})))", regdateExpression).concat("분전"))
	        .when(regdateExpression.between(new Date(now.getTime() - 24 * 3600 * 1000), now))
	        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(HOUR FROM (SYSDATE - {0})))", regdateExpression).concat("시간전"))
	        .when(regdateExpression.between(new Date(now.getTime() - 7 * 24 * 3600 * 1000), now))
	        .then(Expressions.stringTemplate("TO_CHAR(EXTRACT(DAY FROM (SYSDATE - {0})))", regdateExpression).concat("일전"))
	        .otherwise(Expressions.stringTemplate("TO_CHAR({0}, 'YYYY\"년 \"MM\"월 \"DD\"일')", regdateExpression));
	}
	
	private BooleanBuilder withImageCondition(Long userNo, String type) {
	    BooleanBuilder builder = new BooleanBuilder();

	    builder.and(board.writerId().userNo.eq(userNo));
	    builder.and(board.delChk.eq("N"));
	    
	    BooleanExpression existsFiles = JPAExpressions
	    		.selectOne()
	    		.from(boardFile)
	    		.where(boardFile.bno().bno.eq(board.bno))
	    		.exists();
	    
	    if ("text".equals(type)) {
	    	builder.andNot(existsFiles);
	    } else if ("media".equals(type)) {
	    	builder.and(existsFiles);
	    }
	    return builder;
	}
	
	private List<BoardFileDto.response> getBoardFiles(Long bno) {
	    return jpaQueryFactory.select(
	            Projections.constructor(
	                    BoardFileDto.response.class,
	                    boardFile.fno().saveName.as("saveName"),
	                    boardFile.fno().regdate.as("uploadDate")
	            )
	        )
	        .from(boardFile)
	        .leftJoin(boardFile.fno(), fileInfo)
	        .where(boardFile.bno().bno.eq(bno))
	        .fetch();
	}
	
	public boolean hasNextImage(List<BoardDto.infoWithImage> list, int size) {
	    if (list.size() > size) {
	        list.remove(size);
	        return true;
	    }
	    return false;
	}

	public boolean hasNextOutImage(List<BoardDto.response> list, int size) {
	    if (list.size() > size) {
	        list.remove(size);
	        return true;
	    }
	    return false;
	}
}
