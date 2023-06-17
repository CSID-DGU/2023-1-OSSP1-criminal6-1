import csv

# 텍스트 파일 경로
text_file_path = 'similarity/1개 불일치(난이도)_csv파일용.txt'

# CSV 파일 경로
csv_file_path = 'similarity/1개불일치(난이도).csv'

with open(text_file_path, 'r', encoding='utf-8') as file:
    text_data = file.read()

# 데이터 가공
text_data = text_data.replace("'", "")  # 따옴표 제거
text_data = text_data.replace("[[", "")  # 이중 대괄호 제거
text_data = text_data.replace("]]", "")  # 이중 대괄호 제거
text_data = text_data.replace("]\n [", "\n")  # 각 행을 줄바꿈으로 변환
text_data = text_data.replace(" ", ",")  # 공백을 쉼표로 변환
print(text_data)

lines = text_data.split('\n')

with open(csv_file_path, 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(['방ID', '유사도'])  # 헤더 작성
    
    for line in lines:
        room_id, similarity = line.split(',')
        writer.writerow([room_id, similarity])
