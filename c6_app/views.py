
from django.shortcuts import render
# Create your views here.
from rest_framework import viewsets
from .serializers import UserSerializer,RoomSerializer
from .models import AppUser,Room
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


# @api_view(['POST'])
# #들어온 데이터를 db에 저장하는 함수 
# def post_api(request):
#     if request.method == 'POST':
#         serializer = UserSerializer(data = request.data, many=True)
#         #유효성 검사
#         if(serializer.is_valid()):
#             serializer.save()
#             return Response(serializer.data ,status=200)
#         return Response(serializer.errors ,status=status.HTTP_400_BAD_REQUEST)
#         # 유효하지 않으면 에러로 응답

