package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLikeId is a Querydsl query type for LikeId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLikeId extends BeanPath<LikeId> {

    private static final long serialVersionUID = 1126400245L;

    public static final QLikeId likeId1 = new QLikeId("likeId1");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final NumberPath<Integer> likeId = createNumber("likeId", Integer.class);

    public QLikeId(String variable) {
        super(LikeId.class, forVariable(variable));
    }

    public QLikeId(Path<? extends LikeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikeId(PathMetadata metadata) {
        super(LikeId.class, metadata);
    }

}

