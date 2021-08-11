package com.jktoy.fancizToyProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestCreateTable is a Querydsl query type for TestCreateTable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestCreateTable extends EntityPathBase<TestCreateTable> {

    private static final long serialVersionUID = -52600695L;

    public static final QTestCreateTable testCreateTable = new QTestCreateTable("testCreateTable");

    public final StringPath createDesc = createString("createDesc");

    public final StringPath createNm = createString("createNm");

    public final NumberPath<Integer> createSeq = createNumber("createSeq", Integer.class);

    public QTestCreateTable(String variable) {
        super(TestCreateTable.class, forVariable(variable));
    }

    public QTestCreateTable(Path<? extends TestCreateTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestCreateTable(PathMetadata metadata) {
        super(TestCreateTable.class, metadata);
    }

}

