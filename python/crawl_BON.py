import json
import urllib
import pymongo
from bs4 import BeautifulSoup
import requests
import datetime
import re
import time

# pip install 해야하는 것들
# bs4, pymongo, requests, lxml, dnspython

startTime = time.time()

req = requests.get('http://www.kleague.com/')
html = req.text
soup = BeautifulSoup(html, 'lxml')
schedule = soup.select_one('div.nav-menu > div > div > ul > li:nth-child(3) > a')

req = requests.get('http://www.kleague.com' + schedule.attrs['href'])
html = req.text
soup = BeautifulSoup(html, 'lxml')
tables = soup.select('table.table')

# MongoDB 활용
username = 'DA'
password = 'WVFWOfNYCA9S2fHN'

# DB(='cluster' in mongo) name
dbname = 'score_detail'

# Table(='collection' in mongo) name
colname = str(datetime.datetime.now().year) + str(datetime.datetime.now().month)

# Server IP Address
SERVER_IP = 'm15.fvu4o.mongodb.net'

# username, password Encoding (ASCII)
username = urllib.parse.quote_plus(username)
password = urllib.parse.quote_plus(password)

# Mongo DB connection
mongodb = pymongo.MongoClient(
    'mongodb+srv://%s:%s@%s/test?retryWrites=true&w=majority' % (username, password, SERVER_IP))
db = mongodb[dbname]
col = db[colname]

db.drop_collection(colname)


# 한글
hangul = re.compile('[\u3131-\u3163\uac00-\ud7a3]+')

# 경기가 있는 날만 추출
dates = str(soup.find_all('th'))
dates = re.sub('<.+?>', '', dates, 0).strip()  # <th..></th> 이런 태그 없애는 용도
dates = dates.replace(".", "-").replace(" ", "").replace("[", "").replace("]", "")
dates = re.sub(hangul, '', dates)  # 한글로 출력되는 요일 제거
datelist = dates.split(",")

# 현재 날짜 받아오기
now = str(datetime.datetime.now().year) + "-" + str(datetime.datetime.now().month) + "-" + str(datetime.datetime.now().day)

# DB에 넣을 list
mongolist = []

for dt in datelist:
    print(dt)
    # 경기날짜가 현재 날짜보다 미래면 반복문 종료
    if dt >= now:
        break
    match_list = []
    for table in tables:
        if table['id'] == dt:
            for button in table.select('button.btn.btn-outline-blue.btn_matchcenter'):
                match_list.append(button['gs_idx'])
            break
    for match in match_list:
        req = requests.get('http://www.kleague.com/match?vw=live&gs_idx=' + match)
        html = req.text
        soup = BeautifulSoup(html, 'lxml')
        # 통계
        score_board = soup.select_one('div.score-board.clearfix')
        json_input_str = {'날짜':dt}


        hometeam = score_board.select_one('div.team-1 > span').text
        awayteam = score_board.select_one('div.team-2 > span').text
        score = score_board.select_one('div.score > span').text
        json_input_str.update({'score':score})
        json_input_str.update({'hometeam':hometeam})
        json_input_str.update({'awayteam':awayteam})
        json_input_str.update({'경기정보':dt.replace("-","")+hometeam+awayteam})

        statistics_list = soup.select('#sub_tab1 li')
        for statistics in statistics_list:
            title = statistics.select_one('div.title').text.strip()
            score = statistics.select('div.score')
            home = score[0].text.strip()
            away = score[1].text.strip()
            json_input_str.update({title:home+":"+away})

        timeline = soup.select('ul.timeline.list-unstyled li')
        timelinelist = []
        for time in timeline:
            minute = time.select_one('div.min span').text
            context = time.select_one('div.context').text
            str = minute + context
            timelinelist.append(str)
        json_input_str.update({'경기데이터':timelinelist})

        lineup = soup.select_one('div.lineup-body')
        lineup_gk = lineup.select_one('ul.list-unstyled.gk')
        lineup_df = lineup.select_one('ul.list-unstyled.df')
        lineup_mf = lineup.select_one('ul.list-unstyled.mf')
        lineup_fw = lineup.select_one('ul.list-unstyled.fw')
        lineup_bench = lineup.select_one('ul.list-unstyled.bench')

        playerlist_home = []
        for player in lineup_gk.select('div.homeLineUp'):
            playerlist_home.append("gk "+player.text.strip())
        for player in lineup_df.select('div.homeLineUp'):
            playerlist_home.append("df "+player.text.strip())
        for player in lineup_mf.select('div.homeLineUp'):
            playerlist_home.append("mf "+player.text.strip())
        for player in lineup_fw.select('div.homeLineUp'):
            playerlist_home.append("fw "+player.text.strip())
        for player in lineup_bench.select('div.homeLineUp'):
            playerlist_home.append("bench "+player.text.strip())
        json_input_str.update({'homelineup':playerlist_home})

        playerlist_away = []
        for player in lineup_gk.select('div.awayLineUp'):
            playerlist_away.append("gk "+player.text.strip())
        for player in lineup_df.select('div.awayLineUp'):
            playerlist_away.append("df "+player.text.strip())
        for player in lineup_mf.select('div.awayLineUp'):
            playerlist_away.append("mf "+player.text.strip())
        for player in lineup_fw.select('div.awayLineUp'):
            playerlist_away.append("fw "+player.text.strip())
        for player in lineup_bench.select('div.awayLineUp'):
            playerlist_away.append("bench "+player.text.strip())
        json_input_str.update({'awaylineup': playerlist_away})

        mongolist.append(json_input_str)

col.insert_many(mongolist)