
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
                    user = AppUser.objects.get(id=user_id)
                    user.roomID = room
                    user.save()
                    return Response({'success': 'Room created successfully'}, status=status.HTTP_201_CREATED)
                except AppUser.DoesNotExist:
                    return Response({'error': 'User not found'}, status=status.HTTP_404_NOT_FOUND)
            else:
                return Response({'error': 'User ID not found in session'}, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response({'error': 'Missing required fields'}, status=status.HTTP_400_BAD_REQUEST)
        

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
    

