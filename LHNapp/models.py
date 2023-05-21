from django.db import models


#User 모델 정의 
class User(models.Model):
    id = models.CharField(max_length=255, unique=True)
    # id변수 정의
    # CharField는 문자열 필드를 나타내며, max_length 매개변수로 최대 길이를 설정
    # unique=True는 id 필드가 고유해야 함을 나타냄 , 즉 동일한 'id'값을 가지는 다른 사용자가 존재하지 않도록 함
    password = models.CharField(max_length=255)
    name = models.CharField(max_length=255)

    def __str__(self):
        return self.id
    #객체의 문자열을 반환함 

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

#RoomMember 모델 정의 
class RoomMember(models.Model):
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    #oreignKey 필드를 사용하여 Room 모델과의 외래 키 관계를 정의
    user_id = models.CharField(max_length=255)

    def __str__(self):
        return f"Room: {self.room.title}, User ID: {self.user_id}"