import csv

# 텍스트 파일 경로
text_file_path = 'similarity\지역일치_날짜일치_장르일치_결과/1개 불일치(난이도)_csv파일용(기존).txt'

# CSV 파일 경로
csv_file_path = 'similarity/[기존]_1개불일치(난이도).csv'

with open(text_file_path, 'r', encoding='utf-8') as file:
    text_data = file.read()

# 데이터 가공
text_data = text_data.replace(":", ",")  # :이걸 쉼표로 변환
print(text_data)

lines = text_data.split('\n')

with open(csv_file_path, 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(['방ID', '유사도'])  # 헤더 작성
    
    for line in lines:
        room_id, similarity = line.split(',')
        writer.writerow([room_id, similarity])
