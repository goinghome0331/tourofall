# tour of all project

> spring framework와 아파치 머하웃을 사용하여 만든 여행지 추천 웹 서비스


## 소개

> 이 저장소는 spring framework를 기반으로 사용자에게 웹 서비스를 제공하는 코드만 존재 아파치 머하웃을 사용하여 구현된 추천 엔진 서비스는 현재 없는 상황.

### 사용 도구

```
+ ECLIPSE
+ JDK
+ MAVEN
+ MYSQL
```

### MAVEN에서 필요한 dependency

```
+ PARENT에서 spring-boot-starter-parent의 버전 1.5.0.RELEASE
+ spring-boot-starter-web
+ spring-boot-starter-data-jpa
+ spring-boot-starter-security
+ spring-security-taglibs
+ spring-boot-starter-social-facebook
+ spring-social-security
+ spring-boot-starter-tomcat
+ tomcat-embed-jasper
+ jackson-databind
+ jackson-core
+ jackson-annotations
+ json-simple
+ commons-dbcp
+ mysql-connector-java
+ javax.servlet-api
+ jsp-api
+ jstl
+ tiles-extras
+ lombok
+ junit
```

### Data

```
+ 설문조사로 얻은 추천 데이터
+ [한국관광공사](https://www.data.go.kr/dataset/15000496/openapi.do)로부터 얻은 여행지 데이터
```

### 주의 사항

```
+ 공공데이터 트래픽이 제한되어 있으므로 이를 주의한다.
```

### 기능

#### 검색
![search](https://github.com/goinghome0331/tourofall/blob/master/img/search.png)
```
- 한국관광공사에 존재하는 여행지 범위 내에서 검색 가능
- 해당 내용을 선택함으로써 여행지정보를 볼 수 있다.
```

#### 여행지 정보
![info](https://github.com/goinghome0331/tourofall/blob/master/img/info.jpg)
```
- 한국관광공사에서 제공하는 여행지 기본, 사진, 상세, 지도 정보를 볼 수 있다.
```

#### 리뷰 및 평가
![review](https://github.com/goinghome0331/tourofall/blob/master/img/review.jpg)
> 이미지에 있는 데이터는 테스트 용도로 만든 것입니다.
```
- 여행지에 대한 리뷰 및 평가가 가능하며 다른 유저의 것도 볼수 있다.
- 이곳에서의 평가는 따로 평가하는 기능과 같은 것이다.
```

#### 질문 및 답변
![question](https://github.com/goinghome0331/tourofall/blob/master/img/question.jpg)
> 이미지에 있는 데이터는 테스트 용도로 만든 것입니다.
```
- 여행지에 대한 사용자의 질문 및 답변이 가능하며 다른 유저의 것도 볼 수 있다.
```

#### 평가
![eval](https://github.com/goinghome0331/tourofall/blob/master/img/eval.jpg)

```
- 여행지에 평가를 할수 있다.
- 0 - 5까지의 범위이며 이 정보는 추후에 추천할 때 사용된다.
````