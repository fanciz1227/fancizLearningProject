package com.jktoy.fancizToyProject.jpa;

import com.jktoy.fancizToyProject.entity.TeamInfo;
import com.jktoy.fancizToyProject.entity.User;
import com.jktoy.fancizToyProject.team.dto.TeamInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpqlTest {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 반환할 타입이 명확하면 TypedQuery를 그렇지 않으면 Query를 사용
     */
    @Test
    public void jpqlTest() {
        Query query = entityManager.createQuery("SELECT ti FROM TeamInfo ti");
        List<TeamInfo> teamInfoList1 = query.getResultList();
        teamInfoList1.forEach(System.out::println);

        TypedQuery<TeamInfo> typedQuery = entityManager.createQuery("SELECT ti FROM TeamInfo ti", TeamInfo.class);
        List<TeamInfo> teamInfoList2 = typedQuery.getResultList();
        teamInfoList2.forEach(System.out::println);
    }

    /**
     * 파라미터에는 이름기준 파라미터는 앞에 : 를 붙여준다.
     * 위치기준 파라미터는 ?다음에 위치 값을 준다.
     */
    @Test
    public void jpqlParameterTest() {
        int teamId = 1;
        TypedQuery<TeamInfo> typedQuery1 = entityManager.createQuery("SELECT ti FROM TeamInfo ti WHERE ti.teamId = :teamId", TeamInfo.class);
        typedQuery1.setParameter("teamId", teamId);
        List<TeamInfo> teamInfoList1 = typedQuery1.getResultList();
        teamInfoList1.forEach(System.out::println);

        TypedQuery<TeamInfo> typedQuery2 = entityManager.createQuery("SELECT ti FROM TeamInfo ti WHERE ti.teamId = ?1", TeamInfo.class);
        typedQuery2.setParameter(1, teamId);
        List<TeamInfo> teamInfoList2 = typedQuery2.getResultList();
        teamInfoList2.forEach(System.out::println);
    }

    @Test
    public void jpqlPagingTest() {
        TypedQuery<TeamInfo> typedQuery = entityManager.createQuery("SELECT ti FROM TeamInfo ti ORDER BY ti.teamId DESC ", TeamInfo.class);
        typedQuery.setFirstResult(2); //조회 시작 위치 (0부터 시작)
        typedQuery.setMaxResults(3); //조회할 데이터의 수
        typedQuery.getResultList();

        List<TeamInfo> teamInfoList = typedQuery.getResultList();
        teamInfoList.forEach(System.out::println);
    }

    /**
     * projection type
     */
    @Test
    public void jpqlProjectionTest() {
        //Entity projection -> Entity 전체를 조회한다. 이렇게 조회된 Entity는 영속성 컨텍스트에서 관리된다.
        String entityQuery = "SELECT ti FROM TeamInfo ti";
        List<TeamInfo> entityList = entityManager.createQuery(entityQuery, TeamInfo.class).getResultList();
        entityList.forEach(System.out::println);

        //Embedded type projection -> 임베디드 타입은 Entity 타입이 아닌 값 타입으로 직접 조회한 임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다.
        String embeddedQuery = "SELECT ti.teamId FROM TeamInfo ti";
        List<TeamInfo> embeddedList = entityManager.createQuery(embeddedQuery, TeamInfo.class).getResultList();
        embeddedList.forEach(System.out::println);

        //Scala type projection -> 숫자, 문자, 날짜 같은 기본 데이터 타입을 scala라고 한다.
        List<String> scalaList = entityManager.createQuery("SELECT ti.teamName FROM TeamInfo ti", String.class).getResultList();
        scalaList.forEach(System.out::println);

        //new 명령어 -> DTO 생성자를 이용한 매핑
        List<TeamInfoDto> teamInfoDtoList = entityManager
                .createQuery("SELECT new TeamInfoDto(ti.teamId, ti.teamName, ti.teamDescription, ti.teamPhone) FROM TeamInfo ti", TeamInfoDto.class)
                .getResultList();
    }

    /**
     * Jpql Join Test
     */
    @Test
    public void jpqlJoinTest() {
        int teamId = 1;

        //내부 join
        List<User> innerJoinList = entityManager
                .createQuery("SELECT us FROM User us INNER JOIN us.teamInfo ti WHERE ti.teamId = :teamId", User.class)
                .setParameter("teamId", teamId)
                .getResultList();

        innerJoinList.forEach(System.out::println);

        //외부 join, OUTER는 생략 가능하다
        List<User> outerJoinList = entityManager
                .createQuery("SELECT us FROM User us LEFT JOIN us.teamInfo ti", User.class)
                .getResultList();

        outerJoinList.forEach(System.out::println);

        //fetch join
        //사실상 제일 중요한 join.. 객체를 사용하는 자바와 달리 관계형 데이터인 DB간 차이로 인해 하나의 데이터를 검색할때 그에 맞는 join 데이터를 모두 조회하는 경우 N+1 문제가 발생할 수 있다.
        //TeamInfo가 4개 User가 7개인 상황일 때 TeamInfo 1개를 조회할 때 매핑된 User가 7개 조회 되는 현상 N+1이 발생한다.
        //따라서 join할때 모든 데이터를 탐색하지 않게 fetch join을 걸어주면 된다.
        //다만 fetch join은 Entity간 연관관계의 fetchType을 사용할 수 없다. fetch join은 호출시에 모든 연관관계의 데이터를 가져오기 때문에 Lazy설정이 무의미하다.
        //모든 Entity의 내용까지 모두 로딩하기 때문에 성능 저하의 원인이 될 수 있다.
        List<User> fetchJoinList = entityManager
                .createQuery("SELECT us FROM User us JOIN FETCH us.teamInfo ti", User.class).getResultList();

        fetchJoinList.forEach(System.out::println);
    }
}
