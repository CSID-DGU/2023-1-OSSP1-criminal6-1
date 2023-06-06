
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
from django.contrib.auth.models import AppUser
from django.contrib.auth import authenticate

# Create your views here.

#ModelViewSet은 기본적으로 CRUD를 제공함. 
class UserViewSet(viewsets.ModelViewSet):
    queryset = AppUser.objects.all()
    serializer_class = UserSerializer

class RoomViewSet(viewsets.ModelViewSet):
    queryset = Room.objects.all()
    serializer_class = RoomSerializer

class ChatViewSet(viewsets.ModelViewSet):
    queryset = Chat.objects.all()
    serializer_class = ChatSerializer

#회원가입시 호출되는 함수 
@api_view(['POST'])
def post_api(request):
    if request.method == 'POST':
        serializer = UserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            result = {
                'result': 'success'
            }
            return Response(result, status=200)
        else:
            result = {
                'result': 'fail'
            }
            return Response(result, status=400)
        
@api_view(['GET'])
def login(request):
    if request.method == 'POST':
        userID = request.POST['userID']
        id = request.POST['id']
        password = request.POST['password']
        if AppUser is not None:
            auth.login(request, AppUser)
            return redirect('home')
        else:
            return render(request, 'login.html', {'error' : 'username or password is incorrect.'})
    else:
        return render(request, 'login.html')