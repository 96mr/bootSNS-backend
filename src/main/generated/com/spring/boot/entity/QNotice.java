package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 1189472443L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotice notice = new QNotice("notice");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final DateTimePath<java.util.Date> chkdate = createDateTime("chkdate", java.util.Date.class);

    public final StringPath content = createString("content");

    public final StringPath delChk = createString("delChk");

    public final NumberPath<Long> noteNo = createNumber("noteNo", Long.class);

    public final EnumPath<NotificationType> notetype = createEnum("notetype", NotificationType.class);

    public final StringPath noteurl = createString("noteurl");

    protected QMember receiver;

    public final DateTimePath<java.util.Date> regdate = createDateTime("regdate", java.util.Date.class);

    public final NumberPath<Long> sender = createNumber("sender", Long.class);

    public QNotice(String variable) {
        this(Notice.class, forVariable(variable), INITS);
    }

    public QNotice(Path<? extends Notice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotice(PathMetadata metadata, PathInits inits) {
        this(Notice.class, metadata, inits);
    }

    public QNotice(Class<? extends Notice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.receiver = inits.isInitialized("receiver") ? new QMember(forProperty("receiver"), inits.get("receiver")) : null;
    }

    public QMember receiver() {
        if (receiver == null) {
            receiver = new QMember(forProperty("receiver"));
        }
        return receiver;
    }

}

