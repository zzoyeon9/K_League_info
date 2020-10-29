import json
import pymongo
import urllib
from bson.json_util import dumps

username = 'DA'
password = str('WVFWOfNYCA9S2fHN')
SERVER_IP = 'm15.fvu4o.mongodb.net'
username = urllib.parse.quote_plus(username)
password = urllib.parse.quote_plus(password)
client = pymongo.MongoClient('mongodb+srv://%s:%s@%s/' %(username, password, SERVER_IP))
db = client.player
collection = db["player_info"]
    
docs = list(collection.find())
print(docs)