#실시간 채팅 과련 추가
from channels.generic.websocket import AsyncWebsocketConsumer

class ChatConsumer(AsyncWebsocketConsumer):
    async def connect(self):
        self.room_name = self.scope['url_route']['kwargs']['room_name']
        self.room_group_name = 'chat_%s' % self.room_name

        # 해당 방 그룹에 연결합니다.
        await self.channel_layer.group_add(
            self.room_group_name,
            self.channel_name
        )

        await self.accept()

    async def disconnect(self, close_code):
        # 해당 방 그룹에서 연결을 끊습니다.
        await self.channel_layer.group_discard(
            self.room_group_name,
            self.channel_name
        )

    # 메시지를 수신합니다.
    async def receive(self, text_data):
        # 수신한 메시지를 해당 방 그룹으로 전송합니다.
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                'type': 'chat_message',
                'message': text_data
            }
        )

    # 방 그룹으로부터 메시지를 수신합니다.
    async def chat_message(self, event):
        # WebSocket으로 메시지를 전송합니다.
        await self.send(text_data=event['message'])
