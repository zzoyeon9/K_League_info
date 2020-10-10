import urllib
import pymongo
from bs4 import BeautifulSoup
from selenium import webdriver
from datetime import datetime
import json
import re
import time

def matchData():
    print("in?")
    driver.find_element_by_xpath('/html/body/div[3]/div/div[1]/div[2]/div[1]/nav/a[3]').click()

    info = driver.find_element_by_xpath('/html/body/div[2]/div/div[1]').text.split(" / ")
    json_input_str = {'date': info[0]}
    json_input_str.update({'viewer': info[1]})
    json_input_str.update({'place': info[2]})

    team1 = driver.find_element_by_xpath('/html/body/div[2]/div/div[2]/div[1]/span').text  # team1 이름
    team2 = driver.find_element_by_xpath('/html/body/div[2]/div/div[2]/div[3]/span').text  # team2 이름
    matchScore = driver.find_element_by_xpath('/html/body/div[2]/div/div[2]/div[2]/span').text  # 경기 결과
    json_input_str.update({'team1': team1})
    json_input_str.update({'team2': team2})
    json_input_str.update({'score': matchScore})

    score = driver.find_elements_by_class_name('score')  # 경기세부내용
    scorelist = []
    for k in range(1, len(score), 2):
        if (score[k] == ""):
            continue

        if (score[k] != " " and score[k] != "\n"):  # 이게 있어야 점유율이 나옴
            string = score[k].text + ":" + score[k + 1].text
            if (string == ":"):
                continue
            scorelist.append(string)

    title = driver.find_elements_by_class_name('title')
    titlelist = []
    for k in title:
        if (k != " "):
            titlelist.append(k.text)

    for a in range(len(scorelist)):
        json_input_str.update({titlelist[a]: scorelist[a]})

    dt = info[0][0:10].replace("-", "")
    file_path = "./" + dt + team1 + team2 + ".json"
    with open(file_path, 'w') as make_file:
        print("made file : " + file_path)
        json.dump(json_input_str, make_file, indent=4)

    with open(file_path, 'r') as read_file:
        print("read")
        json_data = json.load(read_file)
        print(json_data)

    time.sleep(1)
    driver.back()


start = time.time()

# Chrome Option
# Option1 headless mode
chrome_option = webdriver.ChromeOptions()
chrome_option.add_argument('headless')
chrome_option.add_argument('--disable-gpu')
chrome_option.add_argument('lang=ko_KR')

# Chrome browser
driver = webdriver.Chrome('chromedriver.exe', options=chrome_option)

# 게시물 주소로 이동
driver.get('http://kleague.com/schedule?ch=063054&select_league=1')
html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')

# 한글
hangul = re.compile('[\u3131-\u3163\uac00-\ud7a3]+')

# 경기가 있는 날만 추출
dates = str(soup.find_all('th'))
dates = re.sub('<.+?>', '', dates, 0).strip()  # <th..></th> 이런 태그 없애는 용도
dates = dates.replace(".", "-").replace(" ", "").replace("[", "").replace("]", "")
dates = re.sub(hangul, '', dates)  # 한글로 출력되는 요일 제거

# 날짜 개수
date = soup.select('thead > tr > th')

arr = driver.find_elements_by_css_selector('button.btn.btn-outline-blue.btn_matchcenter')
# print(range(len(arr)))
# arr[1].click()
# time.sleep(1)
# matchData()

for a in arr:
    # 월 뒤로가기
    # driver.find_element_by_xpath('/html/body/div[2]/div/div[1]/div/div[2]/div[1]/a[1]').click()
    a.click()
    time.sleep(1)
    matchData()

end = time.time()
print(end - start)
driver.close()
