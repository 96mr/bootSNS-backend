package com.spring.boot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileInfo is a Querydsl query type for FileInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileInfo extends EntityPathBase<FileInfo> {

    private static final long serialVersionUID = -1699408531L;

    public static final QFileInfo fileInfo = new QFileInfo("fileInfo");

    public final NumberPath<Integer> fno = createNumber("fno", Integer.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath orgName = createString("orgName");

    public final DatePath<java.util.Date> regdate = createDate("regdate", java.util.Date.class);

    public final StringPath saveName = createString("saveName");

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public QFileInfo(String variable) {
        super(FileInfo.class, forVariable(variable));
    }

    public QFileInfo(Path<? extends FileInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileInfo(PathMetadata metadata) {
        super(FileInfo.class, metadata);
    }

}

