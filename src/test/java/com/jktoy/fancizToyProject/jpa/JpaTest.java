package com.jktoy.fancizToyProject.jpa;

import com.jktoy.fancizToyProject.entity.TestCreateTable;
import com.jktoy.fancizToyProject.repository.TeamInfoRepository;
import com.jktoy.fancizToyProject.repository.TestCreateRepository;
import com.jktoy.fancizToyProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}
