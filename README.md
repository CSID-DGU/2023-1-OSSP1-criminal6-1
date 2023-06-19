# 2023-1-OPPS1-전과6범-01
2023년 1학기 공개SW프로젝트01 1조 전과6범입니다. 
# __About Team__
***
|이름|학과|역할|
|----|---|---|
|[이하늘]|컴퓨터공학과|팀장|
|[김예나]|컴퓨터공학과|Backend|
|[박서연]|컴퓨터공학과|Backend|
|[박선유]|컴퓨터공학과|Backend|
|[이준희]|컴퓨터공학과|Frontend|
|[최지현]|컴퓨터공학과|Backend|

## __1. 프로젝트 주제__
<div>
<h4> 기존의 방탈출 메이트 매칭 App 고도화 
</div>

## __2. 기술스택 & Workflow__
***
<span><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/VisualStudioCode-007ACC?style=flat-square&logo=VisualStudioCode&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white"/></span>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white">
<img width="20%" src="https://user-images.githubusercontent.com/87844641/173768618-f9e968a8-1be8-486e-9cf9-0482e664ef9a.png"/>
<img width="20%" src="https://user-images.githubusercontent.com/87844641/173817432-ddb8a924-3e7e-4370-8ed7-79e019a4bf67.png"/>  
<img width="20%" src="https://user-images.githubusercontent.com/89643634/208532397-c4c26abb-af11-44d9-9d66-fa40987feb6b.png"/> 
***
## __3. 개선사항__
***
### (1) 알고리즘의 고도화
### __새로운 알고리즘 다이어 그램__

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
#### 난이도, 공포도, 활동성의 경우 기존의 일치 판단 여부에서 유클리드 거리를 통해서 유사도를 측정함
#### 유클리드 거리의 경우 사용자-방간의 난,공,활 property가 유사할수록 0에 가까우며 유사하지 않을수록 값이 커진다
#### 1/(1+유클리드 거리)를 통해서 두 데이터간 유사도에 따라 0-1사이의 값이 부여된다
## __5. 시연 영상__
***

## __6. 노션 링크__
***

