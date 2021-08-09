fanciz Toy Project

##DevOps
1. Spring boot 2.5.3
2. java 11
3. MySql 8.0.26
4. gradle-7.1.1
5. JUnit5


## 2021-08-01
1. Toy Project Setting

## 2021-08-02 ~ 03
1. Mysql DB connect setting -> jpa, MySql, HikariCP
2. HikariCp Pool Start Complete
3. jpa repository setting & entity data select

## 2021-08-04 ~ 09
1. Without @setter annotaion to Entity update test
- Entity 혹은 dto 사용시에 @setter를 통한 무분별한 set을 왜 지양하는가?
- 무분별한 @setter 사용을 방지하기 위해 어떤 노력이 필요한가. 오브젝트 전체를 모두 업데이트한다면 @setter를 사용해도 되는걸까..?
- 생성자를 통한 특정 값만 업데이트하거나 전체를 변경시에는 builder 패턴을 이용해보는건 어떤가. builder 패턴시에는 4개이상의 데이터 처리 시 추천하고 있는데 4개 이하일때는 setter로 처리하는것이 더 편한가.
- @setter 이용과 생성자 이용에는 어떠한 차이점이 있는가.
- jpa dirty checking을 이용해 업데이트 할때의 상황은 어떠한 상황인가. (배치처리 같이 대량의 정보에 사용하기엔 부적합, 단건 위주의 처리시 용이)

2. Jpa test using Junit5 Test
- 통합 테스트 : @SpringBootTest
- 단위 테스트 : @WebMvcTest(controller), @DataJpaTest(jpa), @RestClientTest(REST), @JsonTest(json)

3. Jpa join Test
- @OneToOne 1:1 join Test
- 즉시 로딩(EAGER), 지연 로딩(LAZY)의 차이는 무엇인가
- EAGER는 데이터를 select 할때 한번의 join된 Entity 모두 검색해온다. 한방에 쿼리문으로 다 긁어온다고 생각하면 될 듯
- 반대로 LAZY는 한번에 모두 가져오지 않고 join된 Entity를 get 해올때 비로소 select 쿼리가 발생한다.
- EAGER는 join된 Entity가 많아질수록 부하가 커진다. LAZY는 select 쿼리가 여러번 발생하지만 Entity가 직접 데이터를 가져와야할때 비로소 쿼리가 발생하기 때문에 원하는 시점에 Query 호출을 구현할 수 있다.
- 따라서 프로세스에 따른 부하가 어떻게 진행되냐에 따라 LAZY와 EAGER를 적절히 분배해야한다. 하지만 특별히 부하가 생기는 상황이 아니면 LAZY를 주로 활용하자.

- @OneToMany @ManyToOne Test, 양방향 단방향 Test
- 부모 Entity에서 사용하는 @OneToMany, 자식 Entity에서 사용하는 @ManyToOne 데이터의 주체에 따라 달라진다.
- 단방향 @OntToMany일 경우 유일하게 FK가 위치한 자식 Entity가 아닌 부모 Entity에 정의된다.
- 양방향일 경우 @OneToMany일때 부모 Entity에서 @JoinColumn이 제거되고 mappedBy속성으로 자식 Entity와의 관계를 설정한다.
- 자식 Entity에서는 @ManyToOne으로 부모와의 관계를 설정한다.
- 양방향일때 lombok @ToString을 이용시 무한루프에 빠질 수 있다.
- Entity에서 @ToString은 최대한 자제하며 생성자를 별도로 string builder를 이용해 재정의 해서 사용하자. 실무에서는 DTO를 통한 데이터 처리가 제일 좋을듯하다.