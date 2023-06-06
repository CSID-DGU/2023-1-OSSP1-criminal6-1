
from django.shortcuts import render
# Create your views here.
from rest_framework import viewsets
from .serializers import UserSerializer, RoomSerializer, ChatSerializer
from .models import AppUser, Room, Chat
from rest_framework import status
from rest_framework.decorators import api_view
from django.http.response import HttpResponse
from rest_framework.response import Response

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