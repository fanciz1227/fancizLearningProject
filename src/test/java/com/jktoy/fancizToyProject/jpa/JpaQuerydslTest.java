package com.jktoy.fancizToyProject.jpa;

import com.jktoy.fancizToyProject.entity.QTeamInfo;
import com.jktoy.fancizToyProject.entity.QUser;
import com.jktoy.fancizToyProject.entity.TeamInfo;
import com.jktoy.fancizToyProject.repository.TeamInfoRepository;
import com.jktoy.fancizToyProject.repository.UserRepository;
import com.jktoy.fancizToyProject.team.dto.TeamInfoDto;
import com.jktoy.fancizToyProject.user.dto.QUserDto;
import com.jktoy.fancizToyProject.user.dto.UserDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

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
     * Querydsl Test
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

    /**
     * Querydsl join Test
     */
    @Test
    public void jpaQuerydslJoinTest() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        List<Tuple> user = jpaQueryFactory
                .select(QUser.user, QTeamInfo.teamInfo)
                .from(QUser.user)
                .join(QTeamInfo.teamInfo).on(QUser.user.teamId.eq(QTeamInfo.teamInfo.teamId))
                .fetch();

        user.forEach(System.out::println);
    }

    /**
     * Querydsl Dto Mapping Test
     * 생성자를 통한 Projection.constructor를 이용하여 Dto와 매핑
     * constructor를 사용할 땐 새로운 컬럼이 추가되었을 시 Dto class에 추가되지 않았을 경우 컴파일 에러가 발생하지 않는다.
     * 런타임때 오류가 발생하기 때문에 개발자가 사전에 오류를 인지하기 어렵다.
     * 따라서 DB컬럼이 추가되는 경우 당연한 얘기지만 Dto class 체크를 더 면밀히 해야한다.
     * teamInfo에 teamPhone을 추가 한 후 Dto 매핑을 하지 않았을때 오류가 발생한다.
     * No constructor found for class com.jktoy.fancizToyProject.team.dto.TeamInfoDto with parameters
     */
    @Test
    public void jpaQuerydslJoinToDtoTest(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        List<TeamInfoDto> teamInfo = jpaQueryFactory
                .select(Projections.constructor(TeamInfoDto.class,
                        QTeamInfo.teamInfo.teamId,
                        QTeamInfo.teamInfo.teamName,
                        QTeamInfo.teamInfo.teamDescription,
                        QTeamInfo.teamInfo.teamPhone,
                        QUser.user))
                .from(QTeamInfo.teamInfo)
                .join(QUser.user).on(QTeamInfo.teamInfo.teamId.eq(QUser.user.teamId))
                .fetch();

        teamInfo.forEach(System.out::println);
    }

    /**
     * User 테이블의 userStatus를 추가했을 시 기존의 QUserDto에는 userStatus가 존재하지 않기 떄문에
     * 컴파일 오류가 발생한다. 따라서 개발자가 사전에 인지 할 수 있지만
     * QUserDto에 새로운 컬럼에 대한 정보가 generated되어야 하기 때문에 Dto에 Querydsl 의존성이 들어간다.
     */
    @Test
    public void jpqQuerydslJoinToDtoQueryProjectionTest() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QUser quser = QUser.user;

        List<UserDto> user = jpaQueryFactory
                .select(new QUserDto(
                        quser.userSeq,
                        quser.userName,
                        quser.userEmail,
                        quser.userPhoneNumber,
                        quser.teamId,
                        QTeamInfo.teamInfo))
                .from(quser)
                .join(QTeamInfo.teamInfo).on(quser.teamId.eq(QTeamInfo.teamInfo.teamId))
                .fetch();
    }
}
