package com.jktoy.fancizToyProject.jpa;

import com.jktoy.fancizToyProject.entity.QTeamInfo;
import com.jktoy.fancizToyProject.entity.TeamInfo;
import com.jktoy.fancizToyProject.repository.TeamInfoRepository;
import com.jktoy.fancizToyProject.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaQuerydslTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamInfoRepository teamInfoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * QueryDSL Test
     */
    @Test
    public void jpaQuerydslTest() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QTeamInfo qTeamInfo = QTeamInfo.teamInfo;

        TeamInfo teamInfo = jpaQueryFactory
                .select(qTeamInfo)
                .from(qTeamInfo)
                .where(qTeamInfo.teamId.eq(1))
                .fetchOne();

        assertEquals(teamInfo.getTeamId(), 1);
    }
}
