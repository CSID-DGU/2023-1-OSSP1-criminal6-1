import numpy as np

# 상중하
room1_difficulty = 3
room1_activity = 3
room1_fear = 3

room2_difficulty = 3
room2_activity = 3
room2_fear = 2

room1 = np.array([room1_difficulty, room1_activity, room1_fear])
room2 = np.array([room2_difficulty, room2_activity, room2_fear])

distance = np.linalg.norm(room1 - room2)

similarity = 1 / (1 + distance)

print(f"두 방의 유사도 (유클리드 거리): {similarity}")
