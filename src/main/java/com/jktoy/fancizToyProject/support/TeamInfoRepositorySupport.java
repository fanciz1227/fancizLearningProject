package com.jktoy.fancizToyProject.support;

import com.jktoy.fancizToyProject.entity.TeamInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


@Repository
public class TeamInfoRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public TeamInfoRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(TeamInfo.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
