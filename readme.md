## CUSTOM ORM

- 목표


  a. @entity , @id 구현 해야된다.  

  b. field -> 데이터베이스와 일치해야합니다.  

  c. 커넥션풀 (hikari db ) 처럼 커넥션풀 만들어서 운용 

  d. 조회까지 성공하기.  


1. JPA REPO 구현체로 Entity를 읽어서  어노테이션을 찾는건 쉽다.
2. ID가 선언된 값의 필드명을 가지고오는건 쉽다.  
3. 결국 이렇게 Id, Entity를 찾아서 매핑 필드를 가져오는건 어렵지않다  
 ㄴ 클래스에서 어떤 값을 어노테이션&리플렉션 으로 찾아오는건 생각보다 괴롭다.  
 ㄴ 저기서 선언한걸 사용하지않고 구현체에서 리플렉션을 돌려서 어노테이션을 찾아오고  
 "" 값일 경우에 따로 조치를 취해주는 식으로 한다면 없거나 있거나.  
 A. tableName이 있을경우 -> 그냥 쓰고  
 B. tableName이 blank -> true 리플렉션의 getSimpleName 사용한다.  
4. Impl을 구현해서 jdbc랑 연동시키면, 1차캐시 부분까지는 연동이 가능할거 같다.  




- 환경 정보

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