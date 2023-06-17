
from django.shortcuts import render, redirect
# Create your views here.
from rest_framework import viewsets
from .serializers import UserSerializer, RoomSerializer, ChatSerializer
from .models import AppUser, Room, Chat
from rest_framework import status
from rest_framework.decorators import api_view
from django.http.response import HttpResponse
from rest_framework.response import Response
from django.contrib import auth

from django.contrib.auth.models import User
from django.contrib.auth import authenticate,login
from django.contrib.sessions.models import Session


# from django.contrib.auth import authenticate
#확인

#ModelViewSet은 기본적으로 CRUD를 제공함. 
class RoomViewSet(viewsets.ModelViewSet):
    queryset = Room.objects.all()
    serializer_class = RoomSerializer
    @api_view(['POST'])
    def roomcreate(request):

        date = request.data.get('date')
        region = request.data.get('region')
        title = request.data.get('title')
        roomIntro = request.data.get('roomIntro')
        genre = request.data.get('genre')
        difficulty = request.data.get('difficulty')
        fear = request.data.get('fear')
        activity = request.data.get('activity')
        
        if date and region and title and roomIntro and genre and difficulty and fear and activity:
            
            room_count = Room.objects.count()
            room = Room.objects.create(
                
                date=date,
                region=region,
                title=title,
                roomIntro=roomIntro,
                genre=genre,
                difficulty=difficulty,
                fear=fear,
                activity=activity
            )

            user_id = request.session.get('user_id')
            if user_id:
                try:
                    room_creator = AppUser.objects.get(id=user_id)
                    room_index = room.roomID              # 방의 인덱스를 가져옴 (0부터 시작)
                    users = AppUser.objects.all()
                    for user in users:
                        roomID = user.roomID            # 사용자의 roomID 가져오기
                        if user == room_creator:        # 방을 만든 사용자인 경우
                            roomID = roomID[:room_index] + '1'  # 해당 위치에 1을 추가
                        else:                           # 방을 만든 사용자가 아닌 경우
                            roomID = roomID[:room_index] + '0' # 해당 위치에 0을 추가
                        user.roomID = roomID            # 사용자의 roomID를 업데이트
                        user.save()
                    return Response({'success': 'Room created successfully'}, status=status.HTTP_201_CREATED)
                except AppUser.DoesNotExist:
                    return Response({'error': 'User not found'}, status=status.HTTP_404_NOT_FOUND)
            else:
                return Response({'error': 'User ID not found in session'}, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response({'error': 'Missing required fields'}, status=status.HTTP_400_BAD_REQUEST)
    
    @api_view(['POST'])
    def roomsearch(request):
        area1 = request.data.get('area1')
        area2 = request.data.get('area2')
        area3 = request.data.get('area3')
        startdate = request.data.get('startdate')
        enddate = request.data.get('enddate')
        genre = request.data.get('genre')
        difficulty = request.data.get('difficulty')
        fear = request.data.get('fear')
        activity = request.data.get('activity')

        if area1 and area2 and area3 and startdate and enddate and genre and difficulty and fear and activity:
            # 필요한 정보를 사용하여 방을 검색 정보를 리스트에 저장
            room_data = [
                {
                'area1': area1,
                'area2': area2,
                'area3': area3,
                'startdate': startdate,
                'enddate': enddate,
                'genre': genre,
                'difficulty': difficulty,
                'fear': fear,
                'activity': activity
                }
            ]
            #return Response(room_data, status=status.HTTP_200_OK)
            return Response({'room_data': room_data}, status=status.HTTP_200_OK)
        else:
            return Response({'error': 'Missing required fields'}, status=status.HTTP_400_BAD_REQUEST)
    
    @api_view(['GET'])
    def getroomlist(request):
        #room_data = request.data.get('room_data')
        room_data = roomsearch(request)
        rooms = Room.objects.all()
        recommended_rooms = []  # 추천된 방을 저장할 리스트

        # 추천 알고리즘을 통해 방을 추천하고 추천 점수를 계산하여 리스트에 추가
        

        # 마지막으로 추천된 3개의 방 정보를 반환
        response_data = []
        for i in range(3):
            if recommended_rooms:
                recommendation = recommended_rooms.pop(0)
                room = recommendation[1]
                room_data = {
                    'roomID': room.roomID,
                    'title': room.title,
                    'roomIntro': room.roomIntro,
                    'date': room.date,
                    'region': room.region,
                    'genre': room.genre,
                    'difficulty': room.difficulty,
                    'fear': room.fear,
                    'activity': room.activity,
                }
                response_data.append(room_data)
            else:
                break

            return Response({'recommended_rooms': response_data}, status=status.HTTP_200_OK)
        else:
            return Response({'error': 'Missing required fields'}, status=status.HTTP_400_BAD_REQUEST)

    @api_view(['POST'])
    def enterroomlist(request):
        user_id = request.session.get('user_id')
        room_id = request.data.get('room_id')

        if user_id and room_id:
            try:
                user = AppUser.objects.get(id=user_id)
                room_index = int(room_id) - 1  # 방의 인덱스를 계산 (0부터 시작)
                if room_index >= 0 and room_index < len(user.roomID):
                    user.roomID = user.roomID[:room_index] + '1' + user.roomID[room_index+1:]  # 해당 위치에 1로 업데이트
                    user.save()
                    return Response({'success': 'Room entered successfully'}, status=status.HTTP_200_OK)
                else:
                    return Response({'error': 'Invalid room ID'}, status=status.HTTP_400_BAD_REQUEST)
            except AppUser.DoesNotExist:
                return Response({'error': 'User not found'}, status=status.HTTP_404_NOT_FOUND)
        else:
            return Response({'error': 'User ID and room ID are required'}, status=status.HTTP_400_BAD_REQUEST)  
        

class ChatViewSet(viewsets.ModelViewSet):
    queryset = Chat.objects.all()
    serializer_class = ChatSerializer

class UserViewSet(viewsets.ModelViewSet):
    queryset = AppUser.objects.all()
    serializer_class = UserSerializer
    
    #회원가입 시 호출되는 함수 
    @api_view(['POST'])
    def signup(request):
        id = request.data.get('id')
        password = request.data.get('password')
        name = request.data.get('name')
        #회원가입시 아이디, 비번, 이름 등록

        if id and password and name:
            try:
                # Check if a user with the provided username already exists
                existing_user = AppUser.objects.get(id=id)
                return Response({'success': 'False'}, status=status.HTTP_400_BAD_REQUEST)
            except AppUser.DoesNotExist:
                # Create a new user
                user = AppUser.objects.create(id=id, password=password, name = name)
                room_count = Room.objects.count()
                roomID = '0' * room_count
                user.roomID = roomID
                user.save()
                return Response({'success': 'True'}, status=status.HTTP_201_CREATED)
        else:
            return Response({'success': 'False'}, status=status.HTTP_400_BAD_REQUEST)
    
    
    #로그인 시 호출되는 함수 
    @api_view(['POST'])
    def login_api(request):
        id = request.data.get('id')
        password = request.data.get('password')

        if id and password:
            try:
                # 로그인 성공
                existing_user = AppUser.objects.get(id=id, password=password)
                user_id = existing_user.id
                request.session['user_id'] = user_id
                return Response({'login success'}, status=status.HTTP_200_OK)
            except AppUser.DoesNotExist:
                #로그인 실패
                return Response({'등록되지 않은 회원입니다.'}, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response({'error': 'Username and password are required'}, status=status.HTTP_400_BAD_REQUEST)

    @api_view(['POST'])
    def get_user_id(request):
        # 세션에서 사용자 ID 가져오기
        user_id = request.session.get('user_id')
        if user_id:
            return Response({'user_id': user_id}, status=status.HTTP_200_OK)
        else:
            return Response({'error': 'User ID not found'}, status=status.HTTP_404_NOT_FOUND)
    

