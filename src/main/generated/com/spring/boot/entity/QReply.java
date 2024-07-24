package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReply is a Querydsl query type for Reply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = -1620805465L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReply reply = new QReply("reply");

    protected QBoard bno;

    public final ListPath<Reply, QReply> children = this.<Reply, QReply>createList("children", Reply.class, QReply.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final StringPath delChk = createString("delChk");

    protected QReply parentId;

    public final StringPath regdate = createString("regdate");

    public final NumberPath<Long> repno = createNumber("repno", Long.class);

    protected QMember writerId;

    public QReply(String variable) {
        this(Reply.class, forVariable(variable), INITS);
    }

    public QReply(Path<? extends Reply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReply(PathMetadata metadata, PathInits inits) {
        this(Reply.class, metadata, inits);
    }

    public QReply(Class<? extends Reply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bno = inits.isInitialized("bno") ? new QBoard(forProperty("bno"), inits.get("bno")) : null;
        this.parentId = inits.isInitialized("parentId") ? new QReply(forProperty("parentId"), inits.get("parentId")) : null;
        this.writerId = inits.isInitialized("writerId") ? new QMember(forProperty("writerId"), inits.get("writerId")) : null;
    }

    public QBoard bno() {
        if (bno == null) {
            bno = new QBoard(forProperty("bno"));
        }
        return bno;
    }

    public QReply parentId() {
        if (parentId == null) {
            parentId = new QReply(forProperty("parentId"));
        }
        return parentId;
    }

    public QMember writerId() {
        if (writerId == null) {
            writerId = new QMember(forProperty("writerId"));
        }
        return writerId;
    }

}

