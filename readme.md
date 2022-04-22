## CUSTOM ORM
### 의의  
JPA의 구동을 최대한 따라가는 느낌? 을 살릴 수 있도록
나만의 ORM 만들기

- 목표  
  a. @entity , @id 으로 검증을 해야한다  
  b. field -> 데이터베이스와 일치해야합니다.  
  c. 조회까지 성공하기.  
  d. 커넥션풀 (hikari db ) 처럼 커넥션풀 만들어서 운용  

### 이번(4/3week) TODO 리스트
1. Annotation Processor - Entity와 Id는 공존해야한다.
2. method 단위로 Lazy 로딩 하기.


### 환경 정보

```bash
$docker pull mysql
$docker \
  run \
  --detach \
  --volume /opt/mysql:/var/lib/mysql \
  --env MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} \
  --env MYSQL_USER=${MYSQL_USER} \
  --env MYSQL_PASSWORD=${MYSQL_PASSWORD} \
  --env MYSQL_DATABASE=${MYSQL_DATABASE} \
  --name ${MYSQL_CONTAINER_NAME} \
  --publish 3306:3306 \
  --name test-db \
  mysql:latest; 
```