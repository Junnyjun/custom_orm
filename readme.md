## CUSTOM ORM

- 목표


  a. @entity , @id 구현 해야된다.  

  b. field -> 데이터베이스와 일치해야합니다.  

  c. 커넥션풀 (hikari db ) 처럼 커넥션풀 만들어서 운용 

  d. 조회까지 성공하기.  


- 환경 정보

```bash
$docker pull nginx
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