from django.conf import settings
from django.urls import include, path, re_path
from rest_framework import routers, permissions
from . import views #views.py import
from drf_yasg.views import get_schema_view
from drf_yasg import openapi
from .views import  RoomViewSet, ChatViewSet, UserViewSet

#API 명세서 자동 생성 drf-yasg
#swagger 정보 설정, 관련 엔드 포인트 추가
#swagger 엔드포인트는 DEBUG Mode에서만 노출
schema_view = get_schema_view(
   openapi.Info(
      title="Criminal6 Project",
      default_version='v1',
      description="Criminal6-project API 문서",
      terms_of_service="https://www.google.com/policies/terms/",
      contact=openapi.Contact(email="contact@snippets.local"),
      license=openapi.License(name="BSD License"),
   ),
   validators=['flex', 'ssv'],
   public=True,
   permission_classes=(permissions.AllowAny,),
)

router = routers.DefaultRouter() 
#DefaultRouter를 설정
router.register('AppUser', views.UserViewSet) 
# #itemviewset 과 item이라는 router 등록
router.register('Room', views.RoomViewSet) 
# #Chat router 추가
router.register('Chat', views.ChatViewSet) 
# router.register('signup', ap)

#swagger을 통한 path 추가
if settings.DEBUG:
    urlpatterns = [
        path('', include(router.urls)),
        re_path(r'^swagger(?P<format>\.json|\.yaml)$', schema_view.without_ui(cache_timeout=0), name='schema-json'),
        re_path(r'^swagger/$', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
        re_path(r'^redoc/$', schema_view.with_ui('redoc', cache_timeout=0), name='schema-redoc')
       
    ]
    urlpatterns += [
      path('signup/', views.UserViewSet.signup, name='user-signup'),
      path('login/', views.UserViewSet.login_api, name='user-login'),
      path('get_user_id/', views.UserViewSet.get_user_id, name='get-user-id'),
      path('roomcreate/',views.RoomViewSet.roomcreate, name='user-roomcreate'),
      path('roomsearch/',views.RoomViewSet.roomsearch, name='roomsearch'),
      path('getroomlist/',views.RoomViewSet.getroomlist, name='getroomlist'),
      path('enterroomlist/',views.RoomViewSet.enterroomlist, name='enterroomlist')
    ]
    
