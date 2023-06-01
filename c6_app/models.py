from django.db import models

class AppUser(models.Model):
    user = models.IntegerField(primary_key=True)
    id = models.CharField(max_length=45)
    password = models.CharField(max_length=45)
    name = models.CharField(max_length=45)
    roomID2 = models.IntegerField()

    class Meta:
        db_table = 'AppUser'

class Room(models.Model):
    roomID = models.IntegerField(primary_key=True)
    title = models.CharField(max_length=45)
    roomIntro = models.CharField(max_length=200, null=True)
    user2 = models.IntegerField()

    class Meta:
        db_table = 'Room'



class Info(models.Model):
    date = models.CharField(max_length=45)
    region1 = models.CharField(max_length=45)
    genre = models.CharField(max_length=45)
    difficulty = models.FloatField(default=0)
    fear = models.FloatField(default=0)
    activity = models.FloatField(default=0)
    roomID = models.IntegerField()

    class Meta:
        db_table = 'Info'


class Chat(models.Model):
    chatID = models.IntegerField(null=True)
    roomID = models.IntegerField(null=True)
    senderID = models.CharField(max_length=45, null=True)
    content = models.TextField(null=True)
    createAT = models.DateTimeField(null=True)
    roomID2 = models.IntegerField()

    class Meta:
        db_table = 'Chat'
