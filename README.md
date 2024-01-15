# 웹/모바일(웹 기술) 스켈레톤 프로젝트
# Petpermint

## 1. 프로젝트 개요
    
    Pet + Peppermint - 페퍼민트는 민간요법으로써 쓰였던 것에 착안하여 화상으로 반려동물의 
    간단한 상태를 확인하는 서비스 성격을 나타내었습니다. 
    반려 동물 진료 상담, 진료 예약, 라이프 케어 서비스를 제공하여 반려 동물의 라이프를 위한 
    케어 서비스 기능을 온라인으로 제공합니다. 
    
## 2. 팀 역할
    1. 김재민 : 프론트엔드, FE 리더, 부팀장, GCP 프론트엔드 배포
    2. 이민지 : 프론트엔드, 문서 작업
    3. 이수형 : 백엔드, BE 리더, 팀장, 데이터 베이스 설계
    4. 이지은 : 백엔드, Rest API, API 문서
    5. 이채은 : 프론트엔드, 디자인
    6. 장선웅 : 백엔드 서버, AWS
    
## 3. Convention

1. Branch Convention

    ```
    git checkout -b S10P11B303-<issue number>-<issue name>
    ```
    
2. Commit Convention
    
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

## 카테고리

| Application | Domain | Language | Framework |
| ---- | ---- | ---- | ---- |
| :white_check_mark: Desktop Web | :black_square_button: AI | :white_check_mark: JavaScript | :white_check_mark: Vue.js |
| :black_square_button: Mobile Web | :black_square_button: Big Data | :black_square_button: TypeScript | :black_square_button: React |
| :white_check_mark: Responsive Web | :black_square_button: Blockchain | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: IoT | :black_square_button: C# | :white_check_mark: Node.js |
| :black_square_button: iOS App | :black_square_button: AR/VR/Metaverse | :black_square_button: Python | :black_square_button: Flask/Django |
| :black_square_button: Desktop App | :black_square_button: Game | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | | :black_square_button: Kotlin | |

<!-- 필수 항목 -->

## 프로젝트 소개

* 프로젝트명: 그룹 비디오 컨퍼런스 서비스
* 서비스 특징: 웹/모바일(웹 기술) 프로젝트를 위한 스켈레톤 프로젝트
* 주요 기능
  - 회원 관리
  - 화상 미팅룸
  - 그룹 채팅
* 주요 기술
  - WebRTC
  - WebSocket
  - JWT Authentication
  - REST API
* 참조 리소스
  * Vuetify: 디자인 전반 적용
  * Vue Argon Design System: 디자인 전반 적용
  * Vue Black Dashboard Pro(유료): 캘린더 컴포넌트 사용
  * AR Core: 구글에서 제공하는 AR 지원 라이브러리. 이미지 인식 및 오버레이 영상에 활용
  * Color Thief: 이미지 색상 추출 라이브러리. 커버 사진 색상 추출 및 배경 변경에 활용
  * Animation.css: CSS 애니메이션 지원 라이브러리. 메인 페이지 진입 애니메이션에 활용
* 배포 환경
  - URL: // 웹 서비스, 랜딩 페이지, 프로젝트 소개 등의 배포 URL 기입
  - 테스트 계정: // 로그인이 필요한 경우, 사용 가능한 테스트 계정(ID/PW) 기입

