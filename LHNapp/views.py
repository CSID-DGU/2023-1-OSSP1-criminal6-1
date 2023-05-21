from django.shortcuts import render

# Create your views here.

import json
from django.http import JsonResponse
#HTTP 요청을 처리하고 JSON 응답을 반환함 
from django.db import IntegrityError
from .models import User


#사용자가 회원가입할 때 호출할 함수
def signup_view(request):
    if request.method == 'POST':
        id = request.POST.get('id')
        password = request.POST.get('password')
        name = request.POST.get('name')

        try:
            user = User.objects.create(id=id, password=password, name=name)
            result = {"result": "success"}
        except IntegrityError:
            #예외처리 
            result = {"result": "fail"}

        return JsonResponse(result, json_dumps_params={'ensure_ascii': False})
        #json 형식으로 변환 후 전달 




#사용자가 로그인할 때 호출할 함수
def signin_view(request):
    if request.method == 'POST':
        #request.method를 사용하여 요청의 HTTP 메서드가 POST인지 확인함. 
        id = request.POST.get('id')
        password = request.POST.get('password')
        #아이디와 비밀번호 가져오기 

        num = User.objects.filter(id=id).count()
        #'id'가 데이터베이스가 존재하는지 확인 

        if num > 0:  # if id exists
            num = User.objects.filter(id=id, password=password).count()
            #비밀번호도 같은 지 확인 
            if num > 0:  # if password exists
                result = {"result": "success"}
            else:  # if password doesn't exist
                result = {"result": "비밀번호가 일치하지 않습니다."}
        else:  # if id doesn't exist
            result = {"result": "존재하지 않는 id입니다."}

        return JsonResponse(result, json_dumps_params={'ensure_ascii': False})
        #'JsonResponse' 객체를 사용하여 결과 딕셔너리를 JSON 형식으로 변환
        #JSON 응답을 반환함. 




# 여기 다듬어야 함!!!!
# chatroom에 사용자 추가하기 

from django.http import HttpResponse
#Django의 HttpResponse를 사용하여 응답을 반환
from .models import Room, RoomMember
#Room 및 RoomMember와 같은 적절한 Django 모델을 import

#장고 view 함수 정의 
def update_room_member(request):
    if request.method == 'POST':
        id = request.POST.get('id')  
        room_id = int(request.POST.get('roomID'))
        #post 요청에서 id와 roomID 가져오기

        room = Room.objects.get(roomID=room_id)
        #Room 모델에서 roomID 값이 일치하면 가져오기

        member_num = int(room.memberNum) + 1 
        #들어온 사람의 수를 증가
        room.memberNum = member_num
        #room의 멤버수 업데이트
        room.save()

        member_num_str = str(member_num)
        #요건 왜 해...?
        update_member_num = "memberID" + member_num_str

        room_member = RoomMember.objects.get(roomID=room_id)
        setattr(room_member, update_member_num, id)
        room_member.save()

    return HttpResponse("Data updated successfully.")
