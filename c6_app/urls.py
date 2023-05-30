
from django.urls import include, path
from rest_framework import routers
from . import views #views.py import

router = routers.DefaultRouter() 
#DefaultRouter를 설정
router.register('AppUser', views.UserViewSet) 
#itemviewset 과 item이라는 router 등록
router.register('Room', views.RoomViewSet) 

urlpatterns = [
    path('', include(router.urls))
]