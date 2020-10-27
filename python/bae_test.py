import json
import pymongo
import urllib

def lambda_handler(event, context):
    # TODO implement
    username = 'DA'
    password = 'WVFWOfNYCA9S2fHN'
    dbname = 'test'
    colname = 'm15_test1'
    SERVER_IP = 'm15.fvu4o.mongodb.net'
    username = urllib.parse.quote_plus(username)
    password = urllib.parse.quote_plus(password)
    client = pymongo.MongoClient('mongodb+srv://%s:%s@%s/test?retryWrites=true&w=majority' % (username, password, SERVER_IP))
    db = client[dbname]
    col = db[colname]
    
    
    insert_doc = {"teamName" : "FC성원", "region" : "한남", "height" : "163"}
    e = json.dumps(event)
    col.insert_one(json.loads(e))
    return {
        'statusCode': 200,
        'body': event
        
    }
