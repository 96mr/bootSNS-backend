package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollow is a Querydsl query type for Follow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollow extends EntityPathBase<Follow> {

    private static final long serialVersionUID = 960204180L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollow follow = new QFollow("follow");

    protected QFollowId id;

    protected QMember mid;

    public final DateTimePath<java.util.Date> regdate = createDateTime("regdate", java.util.Date.class);

    protected QMember tid;

    public QFollow(String variable) {
        this(Follow.class, forVariable(variable), INITS);
    }

    public QFollow(Path<? extends Follow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollow(PathMetadata metadata, PathInits inits) {
        this(Follow.class, metadata, inits);
    }

    public QFollow(Class<? extends Follow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QFollowId(forProperty("id")) : null;
        this.mid = inits.isInitialized("mid") ? new QMember(forProperty("mid"), inits.get("mid")) : null;
        this.tid = inits.isInitialized("tid") ? new QMember(forProperty("tid"), inits.get("tid")) : null;
    }

    public QFollowId id() {
        if (id == null) {
            id = new QFollowId(forProperty("id"));
        }
        return id;
    }

    public QMember mid() {
        if (mid == null) {
            mid = new QMember(forProperty("mid"));
        }
        return mid;
    }

    public QMember tid() {
        if (tid == null) {
            tid = new QMember(forProperty("tid"));
        }
        return tid;
    }

}

