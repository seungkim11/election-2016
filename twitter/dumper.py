import time
from pymongo import MongoClient
from datetime import datetime, timedelta
import json
from bson import Binary, Code
from bson.json_util import dumps

client = MongoClient('localhost', 27017)
db = client['election-2016']

#run every hour
secondsToSleep = 3600

def dumpData(yesterdayStr):
    collectionName = 't' + yesterdayStr

    cursor = db[collectionName].find()
    count = cursor.count()
    print(collectionName + ' found ' + str(count) + ' tweets')


    # dump only if data count is greater than 0
    if count > 0:
        file = open('out/' + yesterdayStr + '.json', 'w')
        file.write('[')

        i = 0
        for document in cursor:
            doc = dumps(document)
            file.write(doc)
            if (i != count - 1):
                file.write(',\n')
            else:
                file.write('\n]')
            i = i + 1

        print('data for ' + yesterdayStr + ' successfully dumped at ' + str(now))

# Run following code when the program starts
if __name__ == '__main__':

    currentDate = str(datetime.now().month) + '_' + str(datetime.now().day)

    while (True):
        #get now and yesterday strings
        now = datetime.now()
        yesterday = now - timedelta(days=1)

        nowStr = str(now.month) + '_' + str(now.day)
        yesterdayStr = str(yesterday.month) + '_' + str(yesterday.day)

        print('today: ' + currentDate + " at "+ str(now.hour)  + ' o\'clock; yesterday: ' + yesterdayStr)

        #if today is passed, dump the past day
        if (yesterdayStr == currentDate):
            #update currentDate
            currentDate = nowStr
            dumpData(yesterdayStr)


        #sleep
        time.sleep(secondsToSleep)
