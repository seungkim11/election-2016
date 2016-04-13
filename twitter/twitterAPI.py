import json
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
import time
from pymongo import MongoClient

with open('auth.json') as auth_file:
    auth_data = json.load(auth_file)

ckey = auth_data['ckey']
csecret = auth_data['csecret']
atoken = auth_data['atoken']
asecret = auth_data['asecret']

client = MongoClient('localhost', 27017)
db = client['election-2016']
tweets_test = db['tweets_test']

dbfile = open('data.json', 'w')
dbfile.write('[\n')
dbfile.close()

class listener(StreamListener):
    def on_data(self, data):
        try:
            tweet = json.loads(data)
            # with open('data.json', 'a') as outfile:
            #     json.dump(tweet, outfile, sort_keys=True, indent=4)
            #     outfile.write(',\n')
            tweets_test.insert_one(tweet)
            return True

        except BaseException, e:
            print 'failed ondata, ' , str(e)
            time.sleep(5)

    def on_error(self, status_code):
        print status_code

auth = OAuthHandler(ckey, csecret)
auth.set_access_token(atoken, asecret)

twitterStream = Stream(auth, listener())
twitterStream.filter(track=['2016 election', 'bernie sanders', 'hilary clinton', 'trump', 'ted cruz', 'democratic party', 'republican party'])

