import numpy as np

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
user_Info = User(None, 2, 3)

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

property_similarity=[[]]

#null값인 property가 몇개인지 검사

if count==0: #null값이 하나도 없을때

    #room_data배열에서 각 행마다 반복 -> 난공활 정보 가져오기
    for room in room_data:  
        room_diff = room_data[1]
        room_horr = room_data[2]
        room_acti = room_data[3]

        #np용 배열로 저장
        room_vector = np.array([room_diff, room_horr, room_acti])
        user_vector = np.array([user_Info.difficuty, user_Info.horror, user_Info.activity])

        #유클리드 거리 계산
        euclidean_distance = np.linalg.norm(room_vector - user_vector)
        similarity = 1 / (1 + euclidean_distance)
        new_array = [room_data[0], similarity]
        #방1개에 대한 최종 유사도는 배열로 저장해준다
        property_similarity.append(new_array)
        print(property_similarity)

elif count==1: #null값이 하나만 있을 때
    if diff_null==1: #null값이 난이도일때
        for room in room_data:
            room_horr = room_data[2]
            room_acti = room_data[3]

            #np용 배열로 저장
            room_vector = np.array([room_horr, room_acti])
            user_vector = np.array([user_Info.horror, user_Info.activity])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room_data[0], similarity]
            #방1개에 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif horr_null==1: #null값이 공포도일때
        for room in room_data:
            room_diff = room_data[1]
            room_acti = room_data[3]

            #np용 배열로 저장
            room_vector = np.array([room_diff, room_acti])
            user_vector = np.array([user_Info.difficulty, user_Info.activity])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room_data[0], similarity]
            #방1개에 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif acti_null==1:
        for room in room_data:
            room_diff = room_data[1]
            room_horr = room_data[2]

            #np용 배열로 저장
            room_vector = np.array([room_diff, room_horr])
            user_vector = np.array([user_Info.difficulty, user_Info.horror])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room_data[0], similarity]
            #방1개마다 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)

elif count==2: #null값이 2개일때
    if diff_null==1 and horr_null==1: #활동성 값만 계산해주면 될때
        for room in room_data:
            room_acti = room_data[3]

            #np용 배열로 저장
            room_vector = np.array([room_acti])
            user_vector = np.array([user_Info.activity])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room_data[0], similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif diff_null==1 and acti_null==1: #공포도 값만 계산해주면 될때
        for room in room_data:
            room_horr = room_data[2]

            #np용 배열로 저장
            room_vector = np.array([room_horr])
            user_vector = np.array([user_Info.horror])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room_data[0], similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)
    elif horr_null==1 and acti_null==1: #난이도 값만 계산해주면 될때
        for room in room_data:
            room_diff = room_data[1]

            #np용 배열로 저장
            room_vector = np.array([room_diff])
            user_vector = np.array([user_Info.difficulty])

            #유클리드 거리 계산
            euclidean_distance = np.linalg.norm(room_vector - user_vector)
            similarity = 1 / (1 + euclidean_distance)
            new_array = [room_data[0], similarity]
            #방1개마다 대한 최종 유사도는 배열로 저장해준다
            property_similarity.append(new_array)
            print(property_similarity)

elif count==3: #3개다 선택안할때 -> 랜덤추천
    print("랜덤으로 방 3개 추천해주어야 함!!!!!!!!!")