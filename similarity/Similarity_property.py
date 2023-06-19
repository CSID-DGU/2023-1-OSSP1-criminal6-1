import numpy as np
import random
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

user_option={
    'area1': '홍대',
    'area2': '강남',
    'area3': '신촌',
    'startdate': '2023/06/08',
    'enddate': '2023/06/18',
    'genre': 'Comedy',
    'difficulty': None,
    'fear': None,
    'activity': None
}
user_option['startdate'] = int(user_option['startdate'].replace('/', ''))
user_option['enddate'] = int(user_option['enddate'].replace('/', ''))

genre_mapping = {
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

user_option['genre'] = genre_mapping.get(user_option['genre'], 0)
#난이도 값 치환
if user_option['difficulty'] == '상':
    user_option['difficulty'] = 3
elif user_option['difficulty'] == '중':
    user_option['difficulty'] = 2
elif user_option['difficulty'] == '하':
    user_option['difficulty'] = 1
else:
    user_option['difficulty'] = None
#공포도 값 치환
if user_option['fear'] == '상':
    user_option['fear'] = 3
elif user_option['fear'] == '중':
    user_option['fear'] = 2
elif user_option['fear'] == '하':
    user_option['fear'] = 1
else:
    user_option['fear'] = None
#활동성 값 치환
if user_option['activity'] == '상':
    user_option['activity'] = 3
elif user_option['activity'] == '중':
    user_option['activity'] = 2
elif user_option['activity'] == '하':
    user_option['activity'] = 1
else:
    user_option['activity'] = None

#user_option의 키값이 맞게 들어갔는지 확인용
#for key, value in user_option.items():
#    print(key, value)

print(user_option)

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
print("확인용 출력: ", diff_null, horr_null, acti_null)

property_similarity=[]

#null값인 property가 몇개인지 검사

if count==0: #null값이 하나도 없을때

    #rooms배열에서 각 행마다 반복 -> 난공활 정보 가져오기
    for room in rooms:  
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
        print(property_similarity)

elif count==1: #null값이 하나만 있을 때
    if diff_null==1: #null값이 난이도일때
        for room in rooms:
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
            print(property_similarity)
    elif horr_null==1: #null값이 공포도일때
        for room in rooms:
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
            print(property_similarity)
    elif acti_null==1:
        for room in rooms:
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
            print(property_similarity)

elif count==2: #null값이 2개일때
    if diff_null==1 and horr_null==1: #활동성 값만 계산해주면 될때
        for room in rooms:
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
            print(property_similarity)
    elif diff_null==1 and acti_null==1: #공포도 값만 계산해주면 될때
        for room in rooms:
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
            print(property_similarity)
    elif horr_null==1 and acti_null==1: #난이도 값만 계산해주면 될때
        for room in rooms:
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
            print(property_similarity)

elif count==3: #3개다 선택안할때 -> 랜덤추천
    print("랜덤으로 방 3개 추천해주어야 함!!!!!!!!!")
    random_room = random.sample(rooms, 3)
    print("랜덤으로 추천드리는 방입니다: ")
    for room in random_room:
        print(room.방ID, room.지역, room.날짜, room.장르, room.난이도, room.공포도, room.활동성)