# younsiktech
- swagger
  - http://localhost:8080/swagger-ui/index.html
- schema
  - table.sql

### 실행환경
- Java 11
- Gradle 6.8
- MySql 5.7
- Timezone(MySql, Spring) : UTC
  
### 빌드방법
``` shell
./gradlew build -x test 
```
- 빌드된 jar 파일 위치 : /build/libs/work_younsiktech-1.0-SNAPSHOT.jar

### 실행방법
- application-dev.yml
  - spring.datasource의 username, password 값 입력
  - db.url의 reader, writer 값 입력

``` shell
java -Dspring.profiles.active=dev -jar /build/libs/*.jar 
```

