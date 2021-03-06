import json
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
from pymongo import MongoClient
from datetime import datetime

with open('auth.json') as auth_file:
    auth_data = json.load(auth_file)

ckey = auth_data['ckey']
csecret = auth_data['csecret']
atoken = auth_data['atoken']
asecret = auth_data['asecret']

client = MongoClient('localhost', 27017)
db = client['election-2016']


class StdOutListener(StreamListener):

    def __init__(self):
        self.switcher = 0

    def on_data(self, data):
        if (self.switcher % 200  == 0):

            tweet = json.loads(data)

	    now = datetime.now()

            collectionName = 't' + str(now.month) + '_' + str(now.day)

            print('put  ', self.switcher, ' at ', now)

            db[collectionName].insert_one(tweet)

            self.switcher += 1
            return True

        else:
            self.switcher += 1
            return True

    def on_error(self, status):
        print(status)

    def on_disconnect(self, notice):
        print('disconnect ', notice, ' at ', datetime.now())


if __name__ == '__main__':
    l = StdOutListener()
    auth = OAuthHandler(ckey, csecret)
    auth.set_access_token(atoken, asecret)

    stream = Stream(auth, l)
    stream.filter(track=['2016 election', 'sanders', 'clinton', 'trump', 'cruz',
                         'democratic party','republican party'])
