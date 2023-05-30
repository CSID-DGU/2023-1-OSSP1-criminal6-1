from django.db import models

# Create your models here.

#AppUser 모델 정의 
class AppUser(models.Model):
    # id = models.AutoField(primary_key=True)
    password = models.CharField(max_length=255)
    name = models.CharField(max_length=255,default='')
    
    def __str__(self):
        return self.name
        

#class 모델 클래스를 설정하면 데이터베이스에 테이블이 생성된다. 

#Room 모델 정의 
class Room(models.Model):
    title = models.CharField(max_length=255)
    region1 = models.CharField(max_length=255)
    region2 = models.CharField(max_length=255)
    region3 = models.CharField(max_length=255)
    date = models.DateField()
    genre = models.CharField(max_length=255)
    difficulty = models.IntegerField()
    fear = models.IntegerField()
    activity = models.IntegerField()
    room_intro = models.TextField()
    member_num = models.IntegerField()
    #필드 정의 (방의 속성을 나타냄)

    def __str__(self):
        return self.title