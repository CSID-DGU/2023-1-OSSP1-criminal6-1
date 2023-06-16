import numpy as np
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
        self.난이도 = self.convert_difficulty(난이도)
        self.공포도 = self.convert_fear(공포도)
        self.활동성 = self.convert_activity(활동성)

    def convert_genre(self, 장르):
        symbol_mapping = {
            'no matter': -1,
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
        return symbol_mapping.get(장르, 0)

    def convert_difficulty(self, 난이도):
        if 난이도 == '상':
            return 3
        elif 난이도 == '중':
            return 2
        elif 난이도 == '하':
            return 1
        else:
            return 0

    def convert_fear(self, 공포도):
        if 공포도 == '상':
            return 3
        elif 공포도 == '중':
            return 2
        elif 공포도 == '하':
            return 1
        else:
            return 0

    def convert_activity(self, 활동성):
        if 활동성 == '상':
            return 3
        elif 활동성 == '중':
            return 2
        elif 활동성 == '하':
            return 1
        else:
            return 0

#데이터베이스에서 조회된 결과를 사용하여 Room 객체 생성
def create_room_object(room_data):
    rooms = []  #rooms=[Room1, Room2, Room3,]
    for data in room_data:
        방ID, 지역, 날짜, 장르, 난이도, 공포도, 활동성 = data
        room = Room(방ID, 지역, 날짜, 장르, 난이도, 공포도, 활동성)
        rooms.append(room)
    return rooms

# 데이터베이스에서 방 정보 조회
# room_data = get_room_data_from_db()
# 일단 임시 데이터
room_data = [
    ('room1','강남', '2023/06/12', 'Adevnture', '상', '중', '하'),
    ('room2','건대', '2023/06/20', 'Comedy', '중', '중', '하'),
    ('room3','대구', '2023/06/18', 'Thriller', '상', '하', '중'),
    ('room4','신촌', '2023/06/18', 'Thriller', '상', '하', '중'),
    ('room5','홍대', '2023/06/11', 'Thriller', '상', '하', '중')
    # 추가적인 방 데이터를 필요한 만큼 추가할 수 있습니다.
]

# Room 객체로 변환
rooms = create_room_object(room_data)

# rooms를 사용하여 작업 수행
for room in rooms:
    print(room.방ID, room.지역, room.날짜, room.장르, room.난이도, room.공포도, room.활동성)

# 데이터베이스 연결 종료
#db_connection.close()

"""
user_option={
    'area1': '홍대',
    'area2': '강남',
    'area3': '신촌',
    'startdate': '2023/06/08',
    'enddate': '2023/06/18',
    'genre': 'Comedy',
    'diff': '상',
    'fear': '하',
    'activity': '중'
}
user_option['startdate'] = int(user_option['startdate'].replace('/', ''))
user_option['enddate'] = int(user_option['enddate'].replace('/', ''))

for room in room_datum:
    room.날짜 = int(room.날짜.replace('/', '')) 

filtered_rooms = []
#filtered_rooms가 장르랑 난,공,활 유사도 측정해야햐는 방들

for room in room_datum:
    for i in range(1, 4):
        if room.지역 == user_option[f'area{i}']:
            if room.날짜 >= user_option['startdate'] and room.날짜 <= user_option['enddate']:
                filtered_rooms.append(room)
            break  # 일치하는 경우를 찾았으므로 루프를 종료합니다.

for room in filtered_rooms: #해당 조건을 만족한 방들만 출력됨
    print(room.방ID, end='  ')

import numpy as np

symbol_mapping = {
    'no matter': -1,
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
[0,0,0,0.288,0,1,0,0,0.0855,0],
[0,0,0.042,0,0.6037,0,1,0.365,0.3394,0],
[0.5007,0,0.1507,0,0.2711,0,0.365,1,0.0564,0.6076],
[0,0,0.0503,0,0.7465,0.0855,0.3394,0.0564,1,0],
[0.7116,0,0.1823,0,0.4714,0,0,0.6076,0,1]
]


#방과 장르 정보(얜 배열이니까 인덱스 번호를 고려해서 라벨번호부여함)

room_genres = {
   "방1": 'no matter',
   "방2": 'no matter',
   "방3": 'Fantasy',
   "방4": 'Romance',
   "방5": 'Thriller',
   "방6": 'Drama',
   "방7": 'Horror',
}


user_genre = 'Comedy'
#for room, room_genre in room_genres.items():
#    print(f"{room}의 장르: {room_genre}")


#새로운 배열 생성
new_array = []

# 사용자 장르 번호를 행으로, 각 방의 장르 번호를 열로 참조하여 값 가져와 새로운 배열에 저장
if user_genre == 'no matter':
    for room, room_genre in room_genres.items():
        new_array.append(0) #해당 방들의 유사도는 다 0으로 저장함        
else :       
    for room, room_genre in room_genres.items():
        row_index=symbol_mapping[user_genre]
        column_index=symbol_mapping[room_genre]
        if column_index == -1 :
            new_array.append(0)
        else :
            similarity_value = genre_similarity[row_index][column_index]
            new_array.append(similarity_value)

# 결과 출력
print(new_array)

#사용자 정보를 받아올 클래스
class User:
    def __init__(self, difficulty=None, horror=None, activity=None):
        self.difficulty = difficulty
        self.horror = horror
        self.activity = activity

#DB에서 받아올 방 정보(지금은 임의로 설정)
room_data=[
    ["방1", int(1), int(1), int(1)], 
    ["방2", int(3), int(3), int(3)],
    ["방3", int(1), int(2), int(3)],
    ["방4", int(2), int(1), int(3)],
    ["방5", int(3), int(2), int(1)]]

#사용자 정보 임의로 설정
user_Info = User(None, None, None)

#null값인 property 구별용 변수
diff_null=0
horr_null=0
acti_null=0

#어떤 property가 null값인지 구별
if user_Info.difficulty is None:
    diff_null=1
if user_Info.horror is None:
    horr_null=1
if user_Info.activity is None:
    acti_null=1
count = diff_null+horr_null+acti_null
print("확인용 출력: ", diff_null, horr_null, acti_null)

property_similarity=[]

#null값인 property가 몇개인지 검사

if count==0: #null값이 하나도 없을때

    #room_data배열에서 각 행마다 반복 -> 난공활 정보 가져오기
    for room in room_data:  
        room_diff = room[1]
        room_horr = room[2]
        room_acti = room[3]

        #np용 배열로 저장
        room_vector = np.array([room_diff, room_horr, room_acti])
        user_vector = np.array([user_Info.difficulty, user_Info.horror, user_Info.activity])

        #유클리드 거리 계산
        euclidean_distance = np.linalg.norm(room_vector - user_vector)
        similarity = 1 / (1 + euclidean_distance)
        new_array = [room[0], similarity]
        #방1개에 대한 최종 유사도는 배열로 저장해준다
        property_similarity.append(new_array)
        print(property_similarity)

elif count==1: #null값이 하나만 있을 때
    if diff_null==1: #null값이 난이도일때
        for room in room_data:
            room_horr = room[2]
            room_acti = room[3]

            #np용 배열로 저장
            room_vector = np.array([room_horr, room_acti])
            user_vector = np.array([user_Info.horror, user_Info.activity])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room[0], similarity]
            #방1개에 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif horr_null==1: #null값이 공포도일때
        for room in room_data:
            room_diff = room[1]
            room_acti = room[3]

            #np용 배열로 저장
            room_vector = np.array([room_diff, room_acti])
            user_vector = np.array([user_Info.difficulty, user_Info.activity])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room[0], similarity]
            #방1개에 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif acti_null==1:
        for room in room_data:
            room_diff = room[1]
            room_horr = room[2]

            #np용 배열로 저장
            room_vector = np.array([room_diff, room_horr])
            user_vector = np.array([user_Info.difficulty, user_Info.horror])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room[0], similarity]
            #방1개마다 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)

elif count==2: #null값이 2개일때
    if diff_null==1 and horr_null==1: #활동성 값만 계산해주면 될때
        for room in room_data:
            room_acti = room[3]

            #np용 배열로 저장
            room_vector = np.array([room_acti])
            user_vector = np.array([user_Info.activity])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room[0], similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif diff_null==1 and acti_null==1: #공포도 값만 계산해주면 될때
        for room in room_data:
            room_horr = room[2]

            #np용 배열로 저장
            room_vector = np.array([room_horr])
            user_vector = np.array([user_Info.horror])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room[0], similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif horr_null==1 and acti_null==1: #난이도 값만 계산해주면 될때
        for room in room_data:
            room_diff = room[1]

            #np용 배열로 저장
            room_vector = np.array([room_diff])
            user_vector = np.array([user_Info.difficulty])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room[0], similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)

elif count==3: #3개다 선택안할때 -> 랜덤추천
    print("랜덤으로 방 3개 추천해주어야 함!!!!!!!!!")
    random_room = random.sample(room_data, 3)
    print("랜덤으로 추천드리는 방입니다: ")
    print(random_room)

genre_similarity = {
    "room1": 0.5,
    "room2": 0.9,
    "room3": 1
}
#각 방의 장르 유사도 예시

property_similarity = {
    "room1": 0.5,
    "room2": 0.1,
    "room3": 1
}
#각 방의 난,공,활 유사도 예시

genre_values = np.array(list(genre_similarity.values()))
property_values = np.array(list(property_similarity.values()))
# 두 유사도의 값을 넘파이 배열로 변환

sum_similarity=genre_values+property_values
# 각 방의 유사도 합을 계산

total_similarity = np.concatenate((genre_values, property_values))

total_similarity = np.reshape(total_similarity, (2, 3))

total_similarity_std=total_similarity.std(axis=0)
# 각 방의 유사도간 표준편차 계산

diff_similarity=sum_similarity-total_similarity_std
print(sum_similarity)
print(total_similarity_std)
print(diff_similarity)
# (두 유사도의 합)-(유사도간 표준편차)

diff_similarity = sum_similarity - total_similarity_std

sorted_diff_similarity = np.argsort(diff_similarity, axis=None)[::-1]  # 크기순으로 정렬된 인덱스

#sorted_diff_similarity = diff_similarity.flatten()[sorted_indices]  # 크기순으로 정렬된 diff_similarity
print(sorted_diff_similarity)
print( )
for i in sorted_diff_similarity :
    print('room'+str(i+1))
"""