package com.jktoy.fancizToyProject.jpa;

import com.jktoy.fancizToyProject.entity.TeamInfo;
import com.jktoy.fancizToyProject.entity.TestCreateTable;
import com.jktoy.fancizToyProject.repository.TeamInfoRepository;
import com.jktoy.fancizToyProject.repository.TestCreateRepository;
import com.jktoy.fancizToyProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @DataJapTest를 활용한 jpa 단위 test
 * 2021-08-06 application.yml에 DB정보를 생성하고 config에 연결을 시켜놓았는데 왜 application.properties에서 DB정보를 찾는가..
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {

    @Autowired
    private TestCreateRepository testCreateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamInfoRepository teamInfoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void getTestCreateTable() {
        testCreateRepository.findAll().forEach(System.out::println);
    }

    /**
     * @DataJpaTest는 기본적으로 @Transactional 어노테이션을 포함하고 있어 자동으로 롤백된다.
     * 데이터 저장을 확인하기 위해서는 @Rollback(false)을 선언하여 롤백 처리를 명시해주자.
     */
    @Test
    @Rollback(value = true)
    public void saveTestCreateTable() {
        TestCreateTable testCreateTable = new TestCreateTable();
        testCreateTable.setCreateNm("jpa test");
        testCreateTable.setCreateDesc("jpa test");

        testCreateRepository.save(testCreateTable);

        testCreateRepository.findAll().forEach(System.out::println);
    }

    /**
     * delete와 deleteById에 대한 차이점을 유의하자
     * deleteById를 사용하면 내부적인 findById 조회 시 값이 없을 경우 EmptyResultDataAccessException 이 발생한다.
     * findById와 delete를 조합하여 예외처리를 직접 커스텀 할 수 있다.
     * 다만 배치처럼 대량의 데이터를 처리 할 경우 select와 delete를 다중으로 하다보면 성능저하의 원인이 있을 수 있다.
     */
    @Test
    public void deleteTestCreateTable() {
        int testId = 4;
        TestCreateTable testCreateTable = testCreateRepository.findById(testId).orElse(null);

        assertNotNull(testCreateTable);

        testCreateRepository.delete(testCreateTable);

        TestCreateTable testCreateTableAfterDelete = testCreateRepository.findById(testId).orElse(null);

        assertNull(testCreateTableAfterDelete);
    }

    /**
     * user, teamInfo 테이블 간 1:1 조회 테스트
     * Entity에서 @OneToOne을 통해 join 데이터 출력
     * team_id라는 동일한 컬럼으로 join 했을때 mapping을 못해주는 오류가 발생한다..
     * referencedColumnName 옵션과 insertable, updateble 옵션을 false로 줘서 조인에 사용하는 컬럼으로만 인식할 수 있게 선언해야 제대로 동작한다.
     */
    @Test
    public void getUserJoinTeamInfo() {
        
        userRepository.findAll().forEach(System.out::println);
    }

    /**
     * 부모 Entity에서 @OneToMany,@ManyToOne 단방향 테스트
     * mappedBy를 통한 양방향 테스트 -> 부모 Entity의 @OneToMany로 연결시 @JoinColumn을 제외하고 mappedBy 속성으로 자식 Entity와 관계를 설정한다.
     * 양방향 연결시 데이터의 주체를 확인하기 어려울 수 있다. 연결관계를 항상 명확히 하는게 좋다.
     * 현재 무한루프에 빠져버렸다.. 이유가 뭘까. -> lombok @toString 사용시 무한루프에 빠진다.
     * Entity에서 @toString은 사용을 자제하고 builder로 생성자를 만들어 사용하거나 DTO를 이용한 데이터 처리를 하도록하자.
     * Entity를 직접 반환하는 것은 피하고 최대한 DTO를 통한 변환을 하도록 하자.
     */
    @Test
    public void getTeamInfoJoinUser() {

        teamInfoRepository.findAll().forEach(System.out::println);
    }

    /**
     * ORM에서는 영속성 컨텍스트라는 일종의 논리적인 임시 저장소 공간을 통해 관계형 데이터를 객체 (object)로 매핑해 관리해준다.
     * 그 중 Data Chasing은 같은 트랜잭션 내에서 동일한 Entity를 조회할 때 첫번째 조회를 가져온 후 캐시로 매핑하고 두번째 조회부터는 캐시에서 데이터를 반환해준다.
     * 따라서 teamInfo1과 teamInfo2는 동일한 객체이다.
     */
    @Transactional
    @Test
    public void equalTeamInfo() {
        TeamInfo teamInfo1 = teamInfoRepository.findById(1).orElse(null);
        TeamInfo teamInfo2 = teamInfoRepository.findById(1).orElse(null);

        assertEquals(teamInfo1, teamInfo2);
    }

    /**
     * 변경 감지 (dirty checking)를 통해 Entity의 값이 변경되었을 경우 자동으로 인지해 DB에 영속화를 진행한다.
     * 영속화를 진행할 때 jpa가 update쿼리를 생성하는데 즉시 반영이 되지 않고 트랜잭션이 종료되는 시점에 완료된다.
     * 영속성 컨텍스트에서 관리되는 Entity는 트랜잭션 단위로 관리되며 생성/수정/삭제에 대한 쿼리를 쌓아두었다가 트랜잭션이 끝나는 시점(commit)에 일괄/순차 처리 된다.
     * 트랜잭션이 끝나는 순간 캐싱된 데이터는 초기화 된다.
     */
    @Transactional
    @Test
    public void dirtyCheckingTest() {
        TeamInfo teamInfo = teamInfoRepository.findById(1).orElse(null);
        teamInfo.setTeamDescription("백엔드 개발 담당팀");

        System.out.println("result : " + teamInfo.getTeamDescription());
    }

    /**
     * 신규 Entity는 아직 관리대상이 아니기때문에 repository를 통해 명시를 선언해주어야 한다.
     */
    @Transactional
    @Test
    public void newEntitySave() {
        LocalDateTime localDateTime = LocalDateTime.now();

        TeamInfo newTeamInfo = TeamInfo.builder()
                .teamName("백엔드개발2팀")
                .teamDescription("백엔드 개발 담당 2팀")
                .regDate(localDateTime)
                .updtDate(localDateTime)
                .build();

        teamInfoRepository.save(newTeamInfo);
    }

    /**
     * 플러시(flush)는 쓰기 지연된 저장소에 쿼리를 쌓지 않고 DB에 바로 실행한다.
     * 하지만 영속성 컨텍스트는 트랜잭션안에서만 동작하기 때문에 영속화는 일어나지 않은 상태이다.
     * flush가 실행되기 전 저장소에 쌓여있던 쿼리를 모두 실행만 할 뿐 영속화 처리는 트랜잭션이 끝나는 시점에 하게되고 플러시 이후에 실행되는 쿼리는 다시 저장소에 쌓인다.
     * 그렇게 다시 쌓인 쿼리는 순차적으로 처리된다.
     */
    @Transactional
    @Test
    public void entityFlushTest() {
        LocalDateTime localDateTime = LocalDateTime.now();

        TeamInfo newTeamInfo1 = TeamInfo.builder()
                .teamName("백엔드개발2팀")
                .teamDescription("백엔드 개발 담당 2팀")
                .regDate(localDateTime)
                .updtDate(localDateTime)
                .build();

        teamInfoRepository.saveAndFlush(newTeamInfo1);

        TeamInfo newTeamInfo2 = TeamInfo.builder()
                .teamName("백엔드개발3팀")
                .teamDescription("백엔드 개발 담당 3팀")
                .regDate(localDateTime)
                .updtDate(localDateTime)
                .build();

        teamInfoRepository.save(newTeamInfo2);
        testEntityManager.flush();

        TeamInfo newTeamInfo3 = TeamInfo.builder()
                .teamName("백엔드개발4팀")
                .teamDescription("백엔드 개발 담당 4팀")
                .regDate(localDateTime)
                .updtDate(localDateTime)
                .build();

        teamInfoRepository.save(newTeamInfo3);
    }
}
