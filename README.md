fanciz Toy Project

##DevOps
1. Spring boot 2.5.3
2. java 11
3. MySql 8.0.26


## 2021-08-01
1. Toy Project Setting

## 2021-08-02 ~ 03
1. Mysql DB connect setting -> jpa, MySql, HikariCP
2. HikariCp Pool Start Complete
3. jpa repository setting & entity data select

## 2021-08-04
1. Without @setter annotaion to Entity update test
- Entity 혹은 dto 사용시에 @setter를 통한 무분별한 set을 왜 지양하는가?
- 무분별한 @setter 사용을 방지하기 위해 어떤 노력이 필요한가. 오브젝트 전체를 모두 업데이트한다면 @setter를 사용해도 되는걸까..?
- 생성자를 통한 특정 값만 업데이트하거나 전체를 변경시에는 builder 패턴을 이용해보는건 어떤가. builder 패턴시에는 4개이상의 데이터 처리 시 추천하고 있는데 4개 이하일때는 setter로 처리하는것이 더 편한가.
- @setter 이용과 생성자 이용에는 어떠한 차이점이 있는가.
- jpa dirty checking을 이용해 업데이트 할때의 상황은 어떠한 상황인가. (배치처리 같이 대량의 정보에 사용하기엔 부적합, 단건 위주의 처리시 용이)