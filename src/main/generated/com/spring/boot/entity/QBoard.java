package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1635298141L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Integer> bno = createNumber("bno", Integer.class);

    public final StringPath content = createString("content");

    public final StringPath delChk = createString("delChk");

    public final ListPath<BoardFile, QBoardFile> files = this.<BoardFile, QBoardFile>createList("files", BoardFile.class, QBoardFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final ListPath<Like, QLike> likes = this.<Like, QLike>createList("likes", Like.class, QLike.class, PathInits.DIRECT2);

    public final DatePath<java.util.Date> regdate = createDate("regdate", java.util.Date.class);

    public final ListPath<Reply, QReply> replies = this.<Reply, QReply>createList("replies", Reply.class, QReply.class, PathInits.DIRECT2);

    protected QMember writerId;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writerId = inits.isInitialized("writerId") ? new QMember(forProperty("writerId"), inits.get("writerId")) : null;
    }

    public QMember writerId() {
        if (writerId == null) {
            writerId = new QMember(forProperty("writerId"));
        }
        return writerId;
    }

}

