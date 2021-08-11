package com.jktoy.fancizToyProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeamInfo is a Querydsl query type for TeamInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeamInfo extends EntityPathBase<TeamInfo> {

    private static final long serialVersionUID = -1971998590L;

    public static final QTeamInfo teamInfo = new QTeamInfo("teamInfo");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath teamDescription = createString("teamDescription");

    public final NumberPath<Integer> teamId = createNumber("teamId", Integer.class);

    public final StringPath teamName = createString("teamName");

    public final DateTimePath<java.time.LocalDateTime> updtDate = createDateTime("updtDate", java.time.LocalDateTime.class);

    public QTeamInfo(String variable) {
        super(TeamInfo.class, forVariable(variable));
    }

    public QTeamInfo(Path<? extends TeamInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamInfo(PathMetadata metadata) {
        super(TeamInfo.class, metadata);
    }

}

