# 웹/모바일(웹 기술) 프로젝트

# Petpermint

### 목차

1. 프로젝트 개요
2. Team member
3. Convention
4. 프로젝트 개발 환경
5. 기술 소개
6. 주요 기능
7. 서비스 화면
8. 설계 과정

## 1. 프로젝트 개요

    Pet + Peppermint - 페퍼민트는 민간요법으로써 쓰였던 것에 착안하여 화상으로 반려동물의
    간단한 상태를 확인하는 서비스 성격을 나타내었습니다.
    반려 동물 진료 상담, 진료 예약, 라이프 케어 서비스를 제공하여 반려 동물의 라이프를 위한
    케어 서비스 기능을 온라인으로 제공합니다.

## 2. Team member

    1. 김재민 : 프론트엔드, FE 리더, 부팀장, GCP 프론트엔드 배포
    2. 이민지 : 프론트엔드, 문서 작업
    3. 이수형 : 백엔드, BE 리더, 팀장, 데이터 베이스 설계
    4. 이지은 : 백엔드, Rest API, API 문서
    5. 이채은 : 프론트엔드, 디자인
    6. 장선웅 : 백엔드 서버, AWS

## 3. Convention

1.  Branch Convention

    ```
    git checkout -b S10P11B303-<issue number>-<issue name>
    ```

2.  Commit Convention

        ```
        git commit -m "S10P11B303-<issue number> <message>"
        ```


        **FrontEnd Commit Message Convention**
        - subject types
            - feat : 기능을 추가한 경우
            - fix : 버그를 수정한 경우
            - docs : readme 등 문서를 수정한 경우
            - style : 컴포넌트 스타일을 수정한 경우
            - refactor : 리팩터링 등 코드를 개선한 경우
            - test : 테스트 코드 관련
            - chore : package.json의 변경이나 dotenv의 요소 변경 등, 모듈의 변경
        - body rule (영어를 사용할 경우)
            - 과거형이 아닌 명령어, 현재형으로
            - 첫번째 문자는 소문자로
            - 마지막은 마침표가 없도록



        **BackEnd Convention (구글 컨벤션)**
        - import 문
            - 와일드 카드(*)로는 가져오지 않음
        - 중괄호 { }
            - 괄호는 if, else, for, do 및 while문에 코드가 없거나 한 줄만 있더라도 생략하지 않음
        - 들여쓰기
            - 들여쓰기는 Tab 한번
        - 변수선언
            - 변수 선언은 한 개씩(for문 제외)
            - 필요할 때 선언 (지역 변수는 처음 사용되는 지점에 가깝게 선언)
        - 배열
            - 선언 배열 선언은 "block-like"하게
            - [] 는 타입에 붙여준다. (String[] args)
        - 클래스 이름
            - UpperCamelCase (명사나 명사구)
        - 메소드 이름
            - lowerCamelCase (메소드 이름은 동사)


        - 상수 이름
            - CONSTANT_CASE

    <!-- 필수 항목 -->

## 4. 프로젝트 개발 환경

- 4-1 개발 프레임 워크

| Application                       | Domain                                | Language                         | Framework                            |
| --------------------------------- | ------------------------------------- | -------------------------------- | ------------------------------------ |
| :white_check_mark: Desktop Web    | :black_square_button: AI              | :white_check_mark: JavaScript    | :white_check_mark: Vue.js            |
| :black_square_button: Mobile Web  | :black_square_button: Big Data        | :black_square_button: TypeScript | :black_square_button: React          |
| :white_check_mark: Responsive Web | :black_square_button: Blockchain      | :black_square_button: C/C++      | :black_square_button: Angular        |
| :black_square_button: Android App | :black_square_button: IoT             | :black_square_button: C#         | :white_check_mark: Node.js           |
| :black_square_button: iOS App     | :black_square_button: AR/VR/Metaverse | :black_square_button: Python     | :black_square_button: Flask/Django   |
| :black_square_button: Desktop App | :black_square_button: Game            | :white_check_mark: Java          | :white_check_mark: Spring/Springboot |
|                                   |                                       | :black_square_button: Kotlin     |                                      |


- 4-2 BackEnd 개발 환경 
```
InteliJ
Spring Boot 2.4.5
Spring boot JPA
Java 11
AWS EC2
AWS RDS
MySQL
Redis

WebRTC
OpenVidu 2.29.0

CI/CD
AWS EC2
Nginx

```

- 4-3 Front end 개발 환경 
```
VisualCode
Node 18.12.0
NVM
Vue2
Vue3
Google Cloud Platform - Compute Engine
```


## 5. 기술 소개

```
1. open vidu

OpenVidu
WebRTC 기술과 Kudento 미디어 서버를 사용하는 화상 웹 개발 플랫폼을 구현하였습니다.   
OpenVidu를 사용하여 화상 상담과 라이브 방송 기능을 개발 하였습니다. DB의 예약 테이블 정보를 통해 Session ID를 만들어 보호자와 수의사가 같은 Session에 접근 할 수 있게 실시간 화상 통화 방을 연결했고 해당 화상 방 안에서 화면공유와 채팅 등의 추가 기능을 제공하였으며 라이브 방송의 경우 호스트와 게스트로 역할을 구분하여 호스트만 마이크와 화면을 제어 가능하도록 권한을 부여하여 전문가 라이브 방송을 구현했습니다.
```



## 6. 주요 기능

<h3>1. 온라인 초기 상담</h3>

기능 설명 : 수의사와 함께하는 온라인 간편 건강 상담기능을 제공합니다.

서비스 대상자 : 주요 서비스 대상자는 반려동물이 병원에 가야하는 상황인지 알고 싶은 보호자, 간단한 건강 관련 상담을 수의사와 하고 싶은 반려 동물 보호자들이 주요 서비스 대상입니다.

구현 방법 : WebRTC, 외부 컴포넌트 라이브러리 (vuetify , Datepicker)로 화면 구성
  
<br>

<h3>2. 병원 진료 예약</h3>
  
- 기능 설명 : 자체 예약 시스템을 구현하여 원하는 방문 날짜, 시간에 진료가능한 병원에 진료 예약을 할 수 있습니다.

- 서비스 대상자 : 주요 서비스 대상자는 동물 병원 방문시 긴 대기시간을 줄이고 싶은 보호자, 진료 스케줄을 효율적으로 관리하고 싶은 수의사 또는 동물 병원입니다.

- 구현 방법 : 외부 컴포넌트 라이브러리 (vuetify , Datepicker)로 화면 구성


<br>
<h3>3. 응급 예약 </h3>

- 기능 설명 : 사용자가 회원가입시 기재한 주소를 기준으로 가장 가까운 응급 진료 예약이 가능한 병원 리스트 5개를 제시합니다. 지도 맵에서 병원의 위치를 바로 확인할 수 있으며 지도의 마커를 선택하면 병원의 세부 정보를 확인하고 예약하기 버튼을 통해 응급 예약을 진행할 수 있습니다.

- 서비스 대상자 : 반려동물이 응급한 의료 처치를 받아야하는 보호자

- 구현 방법 : 카카오맵 API


<br>
<h3>4. 전문가 라이브 방송 </h3>

- 기능 설명 : 전문가의 라이브 방송을 통해 반려동물을 기르는데 필요한 유용하고 정확한 정보를 제공합니다.

- 서비스 대상자 : 반려동물을 기르는데 필요한 정보를 영상으로 전문가와 소통하며 얻고 싶은 반려동물 보호자들이 주요 서비스 대상자입니다.

- 구현 방법 : webRTC


<br>
<h3>5. 반려동물 편의시설 조회 </h3>

- 기능 설명 : 반려동물편의시설 정보 data set에 지정된 위치 좌표를 연산하여 카카오 멥 API를 사용하여 지역, 카테고리를 선택 후 검색 버튼을 클릭하면 검색조건에 맞는 반려동물 동반시설의 위치를 지도상에서 확인할 수 있습니다.

- 서비스 대상자 : 반려동물이 응급한 의료 처치를 받아야하는 보호자

- 구현 방법 : 카카오맵 API, 반려동물편의시설 정보 data set (localdata)


<br>
<h3>6. 펫닥 커뮤니티 </h3>

기능 설명 : 반려동물을 기르는데 필요한 유용한 정보를 주고 받을 수 있는 반려인들만의 커뮤니티를 제공합니다. 게시글을 작성하고 댓글, 좋아요 표시를 할 수 있습니다.

서비스 대상자 : 반려동물을 기르는데 유용한 정보를 얻고 싶은 반려인들이 주요 서비스 대상자가 될 것입니다.






<br>


## 7. 서비스 화면

1. 일반 사용자 회원가입

<img src="./readme_img/signup_user.PNG" alt="normal user signup">

<br>

2. 수의사 회원가입 

<img src='./readme_img/signup_vet.PNG' alt="vet signup">

<br>
3. 일반 사용자 로그인 

<img src="./readme_img/login.PNG" alt="login">
  
<br>

4. 일반 사용자 메인페이지

<img src="./readme_img/mainpage.png" alt="user mainpage">

<br>
6. 수의사 메인페이지

<img src="./readme_img/mainpage_vet.PNG" alt="vet mainpage">

<br>

7. 일반 사용자 예약 카드   

<img src="./readme_img/main_bookcard.PNG" alt="my book">
  
<br>


8. 온라인 상담 
  
<img src="./readme_img/consulting.gif" alt="consulting">  


<br>

9. 초기 상담 페이지

<img src="./readme_img/consult1.PNG" alt="consult1">
<img src="./readme_img/consult2.PNG" alt="consult2">
<img src="./readme_img/consult3.PNG" alt="consult3">


<br>

10. 병원 예약 페이지

<img src="./readme_img/hospital1.PNG" alt="hospital1">
<img src="./readme_img/hospital2.PNG" alt="hospital2">
<img src="./readme_img/hospital3.PNG" alt="hospital3">  


<br>
11. 응급 예약 페이지 

<img src="./readme_img/emergency.PNG" alt="emergency">


<br>


12. 라이브방송 

<img src="./readme_img/live_broad.PNG" alt="livepage">
<img src="./readme_img/live.gif" alt="livebroadcast">


<br>
13. 펫닥 커뮤니티 

<img src="./readme_img/community.png" alt="community">


<br>
14. 반려동물 편의시설 조회

<img src="./readme_img/find_convienience.png" alt="find_places">


<br>
15. 일반 사용자 마이페이지

<img src="./readme_img/mypage_user.PNG" alt="user mypage">
<img src="./readme_img/mypage_user_list.PNG" alt="user schedule">

<br>

16. 수의사 마이페이지 

<img src="./readme_img/mypage_vet.PNG" alt="vet mypage">
<img src="./readme_img/mypage_vet_list.PNG" alt="vet schedule">


<br>

## 8. 설계 과정

1.
2.
3.
4.
5.
