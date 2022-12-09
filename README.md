# makao-gift-backend

[메가테라 레벨테스트] 마카오 선물하기 프로젝트 백엔드 레포지토리

![image](https://user-images.githubusercontent.com/96414086/206620679-ef944a90-a5b9-4dc2-a987-c350bc1e8c81.png)

> 선물로 감사의 마음을 표현하고,
>
> 얼마나 많은 선물을 했는지 주문 내역으로 확인해봅시다!

## 🎬 데모 영상

https://user-images.githubusercontent.com/96414086/206622040-b72b2043-3010-412a-ac86-33fb52f0c366.mov

## 📝 기능 내용

- 상품 목록 조회
- 상품 상세 내역 조회
- 선물하기
- 주문 목록 조회
- 주문 상세 내역 조회

## 📆 작업 기간

- 2022.11.27 ~ 2022.12.09

## 🌿 API

- GET /users/me - 사용자 정보 조회
- GET /products - 상품 목록 조회
- GET /products/{id} - 상품 상세 정보 조회
- GET /orders - 주문 목록 조회
- GET /orders/{id} - 주문 상세 정보 조회
- POST /orders - 주문하기
- POST /users - 회원가입
- POST /session - 로그인
- GET /users?countOnly=true&username={username} - 아이디 중복 확인

## 🛠 기술 스택

- Java
- Spring
- Gradle
- JUnit5
