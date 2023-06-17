import numpy as np
import csv

##기존 알고리즘

class Room:
    def __init__(self, 방ID, 지역, 날짜, 장르, 난이도, 공포도, 활동성):
        self.방ID = 방ID
        self.지역 = 지역
        self.날짜 = 날짜
        self.장르 = 장르
        self.난이도 = 난이도
        self.공포도 = 공포도
        self.활동성 = 활동성

room_data = []

with open('파일경로/파일이름(상대경로)', 'r',encoding='euc-kr') as file:
    reader = csv.reader(file)
    next(reader)  # 첫 번째 줄(필드명)을 건너뜁니다.
    
    for row in reader:
        방ID = row[0]
        지역 = row[1]
        날짜 = row[2]
        장르 = row[3]
        난이도 = int(row[4])
        공포도 = int(row[5])
        활동성 = int(row[6])
        
        room = Room(방ID, 지역, 날짜, 장르, 난이도, 공포도, 활동성)
        room_data.append(room)


# 방 객체의 요소 출력
for room in room_data:
    print("방ID:", room.방ID)
    print("지역:", room.지역)
    print("날짜:", room.날짜)
    print("장르:", room.장르)
    print("난이도:", room.난이도)
    print("공포도:", room.공포도)
    print("활동성:", room.활동성)
    print()

user_option={
    'area1': '홍대',
    'area2': '강남',
    'area3': '신촌',
    'startdate': '2023/06/08',
    'enddate': '2023/06/23',
    'genre': 'ADVENTURE',
    'diff': 2,
    'fear': 0,
    'activity': 2
}

user_option['startdate'] = int(user_option['startdate'].replace('/', ''))
user_option['enddate'] = int(user_option['enddate'].replace('/', ''))

for room in room_data:
    room.날짜 = int(room.날짜.replace('-', '')) 
    

#filtered_rooms가 장르랑 난,공,활 유사도 측정해야햐는 방들
total_similarity=[]


for room in room_data:
    count=0
    for i in range(1, 4):
        if room.지역 == user_option[f'area{i}']:
            count+=1
            break  # 일치하는 경우를 찾았으므로 루프를 종료합니다
            
    if room.날짜 >= user_option['startdate'] and room.날짜 <= user_option['enddate']:
                count+=1 
            
    if(user_option['genre']==room.장르):
        count+=1
    if(user_option['diff']==room.난이도):
        count+=1
    if(user_option['fear']==room.공포도):
        count+=1
    if(user_option['activity']==room.활동성): 
        count+=1
    new_array=[room, count]
    total_similarity.append(new_array)
i=0

for item in total_similarity:
    print(item[0].방ID,':',item[1])

print()

    
# total_similarity를 기준으로 filtered_rooms를 내림차순으로 정렬
total_similarity = sorted(total_similarity, key=lambda x: x[1], reverse=True)
top3_rooms = total_similarity[:3]  # 상위 3개의 방 선택


# 상위 3개의 방 출력
for item in top3_rooms:
    print(item[0].방ID, end='  ')