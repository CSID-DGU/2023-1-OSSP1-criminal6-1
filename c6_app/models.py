from django.db import models

# Create your models here.
#class 모델 클래스를 설정하면 데이터베이스에 테이블이 생성된다. 

#AppUser 모델 정의 
class AppUser(models.Model):
    userID = models.IntegerField(primary_key = True)
    id = models.CharField(max_length = 45, unique = True)
    password = models.CharField(max_length = 45)
    name = models.CharField(max_length = 45, default = '')
    roomID = models.ForeignKey("Room", on_delete=models.CASCADE)
    
    def __str__(self):
        return self.name
        
#Room 모델 정의 
class Room(models.Model):
    roomID = models.IntegerField(primary_key = True)
    title = models.CharField(max_length = 45, default = '')
    roomIntro = models.TextField(max_length = 200, null=True)
    date = models.DateField(max_length = 45)
    region = models.CharField(max_length = 45)
    genre = models.CharField(max_length = 45)
    difficulty = models.FloatField(default = 0)
    fear = models.FloatField(default = 0)
    activity = models.FloatField(default = 0) 
    #필드 정의 (방의 속성을 나타냄)

    def __str__(self):
        return self.roomID
    
#Chat 모델 정의
class Chat(models.Model):
    chatID = models.IntegerField(max_length = 11)
    senderID = models.ForeignKey("AppUser", on_delete=models.CASCADE)
    content = models.TextField()
    createAT = models.DateTimeField()
    roomId = models.ForeignKey("Room", on_delete=models.CASCADE)

    def __str__(self):
        return self.chatID
