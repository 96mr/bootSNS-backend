package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLike is a Querydsl query type for Like
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLike extends EntityPathBase<Like> {

    private static final long serialVersionUID = 1194466874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLike like = new QLike("like1");

    protected QBoard bno;

    protected QMember likeId;

    public final DateTimePath<java.util.Date> regdate = createDateTime("regdate", java.util.Date.class);

    public final StringPath rowId = createString("rowId");

    public QLike(String variable) {
        this(Like.class, forVariable(variable), INITS);
    }

    public QLike(Path<? extends Like> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLike(PathMetadata metadata, PathInits inits) {
        this(Like.class, metadata, inits);
    }

    public QLike(Class<? extends Like> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bno = inits.isInitialized("bno") ? new QBoard(forProperty("bno"), inits.get("bno")) : null;
        this.likeId = inits.isInitialized("likeId") ? new QMember(forProperty("likeId"), inits.get("likeId")) : null;
    }

    public QBoard bno() {
        if (bno == null) {
            bno = new QBoard(forProperty("bno"));
        }
        return bno;
    }

    public QMember likeId() {
        if (likeId == null) {
            likeId = new QMember(forProperty("likeId"));
        }
        return likeId;
    }

}

