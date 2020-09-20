import requests
from bs4 import BeautifulSoup
from selenium import webdriver
import json
import os

#driver setting
driver = webdriver.Chrome('chromedriver.exe')
#driver = webdriver.PhantomJS('phantomjs.exe')
driver.implicitly_wait(3)

driver.get('http://kleague.com/schedule?ch=084647&select_league=1')
#driver.get('https://hannamnote.tistory.com/')
driver.find_element_by_xpath(
    '/html/body/div[2]/div/div[1]/div/div[2]/div[2]/div/table[5]/tbody/tr[2]/td[5]/button[1]'
    ).click()

                        
#req = requests.get('http://data.kleague.com')
req = requests.get('http://kleague.com/schedule?ch=084647&select_league=1')
html = req.text

soup = BeautifulSoup(html, 'html.parser')
Round = soup.select(
    'table > tbody > tr > td'
    )

for Rounds in Round:
    print(Rounds.text)
    print(Rounds.get('td:nth-child'))
 



# 월
#vw_month
#btn_month_prev
#btn_month_next


# 매치센터 들어가는 btn
#\32 020-09-04y > td.btn-match > button.btn.btn-outline-blue.btn_matchcenter

# team 1
#\32 020-09-04y > td.team-match > div > div.team-1

# team 2
#\32 020-09-04y > td.team-match > div > div.team-2

# score
#\32 020-09-04y > td.team-match > div > div.score

# 위치
#\32 020-09-04y > td:nth-child(4)
