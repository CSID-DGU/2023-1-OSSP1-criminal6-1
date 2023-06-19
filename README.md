# 2023-1-OSSP1-전과6범-01
2023년 1학기 공개SW프로젝트01 1조 전과6범입니다.

# __About Team__
***
|이름|학과|역할|개인 레포|
|----|---|---|---|
|이하늘|컴퓨터공학과|팀장, Backend|https://github.com/cie10|
|김예나|컴퓨터공학과|Backend|https://github.com/rladpskoa|
|박서연|컴퓨터공학과|Backend|https://github.com/LucaSeoyoun|
|박선유|컴퓨터공학과|Backend|https://github.com/sunyu0216
|이준희|컴퓨터공학과|Frontend|https://github.com/l2zh|
|최지현|컴퓨터공학과|Backend|https://github.com/ch8930|

## __1. 프로젝트 주제__
<div>
<h4> 기존의 방탈출 메이트 매칭 App 고도화 
</div>
    
#### 선행프로젝트- __펀칭앱__ (https://github.com/CSID-DGU/2022-2-OSSP1-Exit-1.git)
## __2. 기술스택 & Workflow__
***
<span><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/VisualStudioCode-007ACC?style=flat-square&logo=VisualStudioCode&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/Django-092E20?style=for-the-badge&logo=Django&logoColor=white"></span><br/>
<span><img width="20%" src="https://user-images.githubusercontent.com/87844641/173768618-f9e968a8-1be8-486e-9cf9-0482e664ef9a.png"/></span>
<span><img width="20%" src="https://user-images.githubusercontent.com/87844641/173817432-ddb8a924-3e7e-4370-8ed7-79e019a4bf67.png"/></span> 
<span><img width="20%" src="https://user-images.githubusercontent.com/89643634/208532397-c4c26abb-af11-44d9-9d66-fa40987feb6b.png"/></span> 
***
## __3. 개선사항__

### (1) 알고리즘의 고도화
### __새로운 알고리즘 다이어 그램__
<p align="left">
  <img width="407" alt="KakaoTalk_20230619_151026083" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/101847731/f090ba66-3cd1-4874-a157-90641318a4e2">
</p>

### 기존 프로젝트의 알고리즘 다이어그램을 최대한 유지하되 
### 추가적으로 장르와 난,공,활에 해당하는 property 유사도 결과값 처리 과정을 개선하여주었다.
#### - __장르 유사도 할당 과정__
<pre><code>genre_mapping = {
    'Adventure': 0,
    'Comedy': 1,
    'Fantasy': 2,
    'Romance': 3,
    'Thriller': 4,
    'Drama': 5,
    'Horror': 6,
    'Sci-fi': 7,
    'Mystery': 8,
    'Action': 9
}

genre_similarity=[[1,0,0.6604,0,0,0,0,0.5007,0,0.7116],
[0,1,0,0.3459,0,0,0,0,0,0],
[0.6604,0,1,0,0,0,0.042,0.1507,0.0503,0.1823],
[0,0.3459,0,1,0,0.2879,0,0,0,0],
[0,0,0,0,1,0,0.6037,0.2711,0.7465,0.4714],
[0,0,0,0.288,0,1,0,0,0.0855,0]
[0,0,0.042,0,0.6037,0,1,0.365,0.3394,0],
[0.5007,0,0.1507,0,0.2711,0,0.365,1,0.0564,0.6076],
[0,0,0.0503,0,0.7465,0.0855,0.3394,0.0564,1,0],
[0.7116,0,0.1823,0,0.4714,0,0,0.6076,0,1]
]</code></pre>
#### __위의 배열은 장르-장르간 유사도를 저장한 10*10 크기의 배열__
##### 1. 사용자와 각 방의 장르에 해당하는 숫자로 mapping
##### 2.사용자의 장르에서 mapping된 숫자가 행의 값, 방으로부터 mapping된 숫자가 열의 값
##### 3. 사용자-방간의 genre_similarity 할당



#### - __난이도, 공포도, 활동성 유사도 할당 과정__
  * 난이도, 공포도, 활동성의 경우 기존의 일치 판단 여부에서 __유클리드 거리__ 를 통해서 유사도를 측정함
  * 유클리드 거리의 경우 사용자-방간의 난,공,활 property가 유사할수록 0에 가까우며 유사하지 않을수록 값이 커진다.
  * 1/(1+유클리드 거리)를 통해서 두 데이터간 유사도에 따라 __0-1__ 사이의 값이 부여된다.

###  __정량적 성능평가__
- 데이터간 중복 정도를 나타낼 수 있는 지표인 지니 계수(Gini coefficient)를 통해
  기존 알고리즘대비 개선 알고리즘의 성능평가를 실시함
#### __테스트 케이스 결과__
* 정량적 평가 결과
  * 테스트 케이스및 지니 계수 결과 엑셀 파일의 경우 깃허브에 업로드 되어 있음
![정량적 평가](https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/101847731/a8882ed2-6f8e-4a18-bb38-f114cf6146cb)

### (2)백엔드 수정 및 개선 사항 
추천 알고리즘의 위치를 프론트에서 백엔드로 이동 
    <img width="824" alt="image" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/111051264/b03372bf-8abe-423e-85c7-485a43d86c53">

데이터베이스 ERDiagram<br/>
    <img width="824" alt="데이터베이스 ERD" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/129152167/237ea868-e1c8-4255-99c1-1ae7f015a6e8">
    
    - 빠른 프로세싱 속도로 데이터 처리와 성능을 향상
    - 알고리즘 유지 보수성 개선
    - 알고리즘의 유연성과 확장성을 높임


### (3)프론트엔드 수정 및 개선 사항

#### 수정사항 (매칭 알고리즘에 영향을 미치는 오류들을 고침)
    - 캘린더 날짜 선택 오류
    - 방찾기 진행 시 앱 비정상 종료      
    - 활동성 선택 항목 부분 오류

#### 개선사항 (사용자 친화적 UI/UX)

|  기존 UI/UX  |   개선 UI/UX    | 추가 레이아웃 (매칭 실패시 화면) |
| :-------------: | :----------: | :----------: |
| <img width="189" alt="image" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/17822491-1cda-4842-b53c-4ca966116abb"> | <img width="258" alt="image" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/da86f3f6-d398-4993-b497-9e2b4f7071d6"> | <img width="116" alt="image" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/b4b680bf-b110-44f6-b7e6-f0869196ed5f"> |
    

#### 서버통신
-api 명세서를 토대로 Retrofit2 라이브러리 사용하여 기능별로 서버통신 구현 (서버통신 pr 올려놓았습니다.)

***

## __4. SW 개발 보안__

|  **HTTPS 웹 보안**  |   **서버주소 감추기**    | **사용자 정보 보안 강화** |
| :-------------: | :----------: | :----------: |
| ![image](https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/8f2a85a9-583d-46fc-8816-eb524758c782) <br/> <img width="332" alt="image" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/ca043e04-62ed-49e4-a17d-a84d37a210ed"> |<img width="550" alt="image" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/9c07c3cd-2ed0-4942-a68f-94750d339509"> | <img width="116" alt="image" src="https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/b4b680bf-b110-44f6-b7e6-f0869196ed5f"> |
| **설명** | **설명** | **설명** |
| AWS가 제공하는 ACM 서비스로 인증서 발급​<br/><br/> Application load balancer 생성 후 이를 routing하는 레코드 추가 생성 | 서버 주소를 local.properties에 두어​ 서버 주소가<br/> 노출되는 것 방지​ | 프론트엔드에 모두 저장되어 있던 추천 알고리즘을 ​백엔드에 저장해 유지보수 및 보안 강화​​|

***
## __5. 시연 영상__

### 방찾기 및 채팅방 입장 시연영상
https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/e0aa29ca-065c-40f8-96c0-b327372167ab

### 방 생성 시연영상
https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/a5e9cf97-d888-48c1-9794-c38a887132d5

### 방 찾기 시 매칭될 방 없을때 시연영상
https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/assets/113578158/a53b7164-7aa7-4e48-82e4-70c388d3d7e8



***




## __6. 노션 링크__
[Notion](https://cake-nerine-a2f.notion.site/8a6d4edc900d4ddeb7c345a1fa8f800b?pvs=4)
***
