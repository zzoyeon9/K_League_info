import json
import pymongo
import urllib
from bson.json_util import dumps

def lambda_handler(event, context):
    username = 'DA'
    password = 'WVFWOfNYCA9S2fHN'
    SERVER_IP = 'm15.fvu4o.mongodb.net'
    
    dbname = 'player'
    colname = 'player_info'

    username = urllib.parse.quote_plus(username)
    password = urllib.parse.quote_plus(password)
    client = pymongo.MongoClient('mongodb+srv://%s:%s@%s/player?retryWrites=true&w=majority' % (username, password, SERVER_IP))
    db = client[dbname]
    col = db[colname]
    
    player = col.find(event)

    player_arr = []
    for i in player:
        new_dict = {}
        for k,d in i.items():
            if k=='belong' or k=='name' or k=='position' or k=='img_src' :
                new_dict.update({k:d})
        player_arr.append(new_dict)

    return {
        'statusCode': 200,
        'body': dumps(player_arr)
    }