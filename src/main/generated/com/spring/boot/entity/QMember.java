package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1151392893L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final StringPath birth = createString("birth");

    public final StringPath email = createString("email");

    public final ListPath<Follow, QFollow> follower = this.<Follow, QFollow>createList("follower", Follow.class, QFollow.class, PathInits.DIRECT2);

    public final ListPath<Follow, QFollow> following = this.<Follow, QFollow>createList("following", Follow.class, QFollow.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath privacy = createString("privacy");

    protected QProfile profile;

    public final DatePath<java.util.Date> regdate = createDate("regdate", java.util.Date.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile"), inits.get("profile")) : null;
    }

    public QProfile profile() {
        if (profile == null) {
            profile = new QProfile(forProperty("profile"));
        }
        return profile;
    }

}

