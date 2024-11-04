package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardLike is a Querydsl query type for BoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardLike extends EntityPathBase<BoardLike> {

    private static final long serialVersionUID = -1411748134L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardLike boardLike = new QBoardLike("boardLike");

    protected QBoard bno;

    protected QLikeId id;

    protected QMember likeId;

    public final DateTimePath<java.util.Date> regdate = createDateTime("regdate", java.util.Date.class);

    public QBoardLike(String variable) {
        this(BoardLike.class, forVariable(variable), INITS);
    }

    public QBoardLike(Path<? extends BoardLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardLike(PathMetadata metadata, PathInits inits) {
        this(BoardLike.class, metadata, inits);
    }

    public QBoardLike(Class<? extends BoardLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bno = inits.isInitialized("bno") ? new QBoard(forProperty("bno"), inits.get("bno")) : null;
        this.id = inits.isInitialized("id") ? new QLikeId(forProperty("id")) : null;
        this.likeId = inits.isInitialized("likeId") ? new QMember(forProperty("likeId"), inits.get("likeId")) : null;
    }

    public QBoard bno() {
        if (bno == null) {
            bno = new QBoard(forProperty("bno"));
        }
        return bno;
    }

    public QLikeId id() {
        if (id == null) {
            id = new QLikeId(forProperty("id"));
        }
        return id;
    }

    public QMember likeId() {
        if (likeId == null) {
            likeId = new QMember(forProperty("likeId"));
        }
        return likeId;
    }

}

