import numpy as np
import random
import csv
## 개선 알고리즘

"""
import mysql.connector

# MySQL 데이터베이스에 연결
db_connection = mysql.connector.connect(
  host="localhost",
  user="yourusername",
  password="yourpassword",
  database="yourdatabase"
)

# 데이터베이스에서 방 정보 조회
def get_room_data_from_db():
    cursor = db_connection.cursor()
    sql = "SELECT 방ID, 지역, 날짜, 장르, 난이도, 공포도, 활동성 FROM rooms"
    cursor.execute(sql)
    result = cursor.fetchall()
    cursor.close()
    return result
"""
class Room:
    def __init__(self, 방ID, 지역, 날짜, 장르, 난이도, 공포도, 활동성):
        self.방ID = 방ID
        self.지역 = 지역
        self.날짜 = 날짜
        self.장르 = self.convert_genre(장르)
        self.난이도 = 난이도
        self.공포도 = 공포도
        self.활동성 = 활동성

    def convert_genre(self, 장르):
        symbol_mapping = {
            'ADVENTURE': 0,
            'COMEDY': 1,
            'FANTASY': 2,
            'ROMANCE': 3,
            'THRILLER': 4,
            'DRAMA': 5,
            'HORROR': 6,
            'SF': 7,
            'MYSTERY': 8,
            'ACTION': 9
        }
        return symbol_mapping.get(장르, -1)

# 데이터베이스에서 방 정보 조회
# room_data = get_room_data_from_db()
# 일단 임시 데이터

room_data = []

with open('파일경로/파일이름(상대경로임)','r',encoding='euc-kr') as file:
    reader=csv.reader(file)
    next(reader)
    
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
    'genre': 'Adventure',
    'difficulty': 2,
    'fear': 0,
    'activity': 2
}

user_option['startdate'] = int(user_option['startdate'].replace('/', ''))
user_option['enddate'] = int(user_option['enddate'].replace('/', ''))

genre_mapping = {
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

user_option['genre'] = genre_mapping.get(user_option['genre'], -1)


for room in room_data:
    room.날짜 = int(room.날짜.replace('-', '')) 

filtered_rooms = []
#filtered_rooms가 장르랑 난,공,활 유사도 측정해야햐는 방들

for room in room_data:
    for i in range(1, 4):
        if room.지역 == user_option[f'area{i}']:
            if room.날짜 >= user_option['startdate'] and room.날짜 <= user_option['enddate']:
                filtered_rooms.append(room)
            break  # 일치하는 경우를 찾았으므로 루프를 종료합니다.

print("필터링된 방...출력....")
for room in filtered_rooms:
    print("방ID:", room.방ID)
    print("지역:", room.지역)
    print("날짜:", room.날짜)
    print("장르:", room.장르)
    print("난이도:", room.난이도)
    print("공포도:", room.공포도)
    print("활동성:", room.활동성)
    print()
print( )
print( )
#장르 유사도 출력단계

filtered_genre_similarity = []

genre_similarity=[[1,0,0.6604,0,0,0,0,0.5007,0,0.7116],
[0,1,0,0.3459,0,0,0,0,0,0],
[0.6604,0,1,0,0,0,0.042,0.1507,0.0503,0.1823],
[0,0.3459,0,1,0,0.2879,0,0,0,0],
[0,0,0,0,1,0,0.6037,0.2711,0.7465,0.4714],
[0,0,0,0.288,0,1,0,0,0.0855,0],
[0,0,0.042,0,0.6037,0,1,0.365,0.3394,0],
[0.5007,0,0.1507,0,0.2711,0,0.365,1,0.0564,0.6076],
[0,0,0.0503,0,0.7465,0.0855,0.3394,0.0564,1,0],
[0.7116,0,0.1823,0,0.4714,0,0,0.6076,0,1]
]

if user_option['genre'] == -1:
    for room in filtered_rooms:
        new_array = [room.방ID, 0]
        filtered_genre_similarity.append(new_array)
else:
    for room in filtered_rooms:
        column_index = room.장르
        similarity_value = genre_similarity[user_option['genre']][column_index]
        new_array = [room.방ID, similarity_value]
        filtered_genre_similarity.append(new_array)


#-------------난공활 유사도 측정 부분----------------------------------------------

#null값인 property 구별용 변수
diff_null=0
horr_null=0
acti_null=0

#어떤 property가 null값인지 구별
if user_option.get('difficulty') is None:
    diff_null=1
if user_option.get('fear') is None:
    horr_null=1
if user_option.get('activity') is None:
    acti_null=1
count = diff_null+horr_null+acti_null

 

property_similarity=[]

    #null값인 property가 몇개인지 검사

if count==0: #null값이 하나도 없을때

    #rooms배열에서 각 행마다 반복 -> 난공활 정보 가져오기
    for room in filtered_rooms:  
        room_diff = room.난이도
        room_horr = room.공포도
        room_acti = room.활동성

        #np용 배열로 저장            
        room_vector = np.array([room_diff, room_horr, room_acti])
      
        user_vector = np.array([user_option['difficulty'], user_option['fear'], user_option['activity']])

        #유클리드 거리 계산
        euclidean_distance = np.linalg.norm(room_vector - user_vector)
        similarity = 1 / (1 + euclidean_distance)
        new_array = [room.방ID, similarity]
        #방1개에 대한 최종 유사도는 배열로 저장해준다
        property_similarity.append(new_array)
        

elif count==1: #null값이 하나만 있을 때
    if diff_null==1: #null값이 난이도일때
        for room in filtered_rooms:
            room_horr = room.공포도
            room_acti = room.활동성

            #np용 배열로 저장
            room_vector = np.array([room_horr, room_acti])
            user_vector = np.array([user_option['fear'], user_option['activity']])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room.방ID, similarity]
            #방1개에 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
           
    elif horr_null==1: #null값이 공포도일때
        for room in filtered_rooms:
            room_diff = room.난이도
            room_acti = room.활동성

            #np용 배열로 저장
            room_vector = np.array([room_diff, room_acti])
            user_vector = np.array([user_option['difficulty'], user_option['activity']])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room.방ID, similarity]
            #방1개에 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
           
    elif acti_null==1:
        for room in filtered_rooms:
            room_diff = room.난이도
            room_horr = room.공포도

            #np용 배열로 저장
            room_vector = np.array([room_diff, room_horr])
            user_vector = np.array([user_option['difficulty'], user_option['fear']])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room.방ID, similarity]
            #방1개마다 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            

elif count==2: #null값이 2개일때
    if diff_null==1 and horr_null==1: #활동성 값만 계산해주면 될때
        for room in filtered_rooms:
            room_acti = room.활동성

            #np용 배열로 저장
            room_vector = np.array([room_acti])
            user_vector = np.array([user_option['activity']])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room.방ID, similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            
    elif diff_null==1 and acti_null==1: #공포도 값만 계산해주면 될때
        for room in filtered_rooms:
            room_horr = room.공포도

            #np용 배열로 저장
            room_vector = np.array([room_horr])
            user_vector = np.array([user_Info.horror])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room[0], similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            
    elif horr_null==1 and acti_null==1: #난이도 값만 계산해주면 될때
        for room in filtered_rooms:
            room_diff = room.난이도

            #np용 배열로 저장
            room_vector = np.array([room_diff])
            user_vector = np.array([user_option['difficulty']])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room.방ID, similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            

elif count==3:
     for room in filtered_rooms:
        new_array = [room.방ID, 0]
        property_similarity.append(new_array)


#-------------최종 결과 출력---------------

if all(value == 0 for _, value in filtered_genre_similarity) and (count == 3 or all(value ==0 for _, value in property_similarity)):
    random_room = random.sample(rooms, 3)
    print("랜덤으로 추천드리는 방입니다: ")
    for room in random_room:
        print(room.방ID, room.지역, room.날짜, room.장르, room.난이도, room.공포도, room.활동성)
else :    
# filtered_genre_similarity와 property_similarity에서 방 ID와 유사도 값을 가져옴
    room_IDs = [item[0] for item in filtered_genre_similarity]
    genre_values = np.array([item[1] for item in filtered_genre_similarity])
    property_values = np.array([item[1] for item in property_similarity])

# 방 ID별로 유사도 값들을 합산
    sum_similarity = genre_values + property_values
    

# 유사도 값들의 표준 편차 계산
    total_similarity = np.concatenate((genre_values, property_values))

    total_similarity = np.reshape(total_similarity, (2,len(room_IDs)))
    print(total_similarity)
    total_similarity_std = np.std(total_similarity, axis=0)
    array_shape = total_similarity_std

# 유사도 값들의 합에서 표준 편차를 뺀 결과를 2차원 배열로 저장
    modified_values = np.column_stack((room_IDs, sum_similarity - total_similarity_std))
    
    recommended_rooms = modified_values[np.argsort(modified_values[:, 1])[::-1]]
    print(recommended_rooms)
# modified_values를 크기순으로 정렬하여 상위 3개 방 추천
    recommended_rooms = modified_values[np.argsort(modified_values[:, 1])[::-1]][:3]
    
# 추천된 방 출력
    for room in recommended_rooms:
        print("Room ID:", room[0], "- Recommendation Score:", room[1])