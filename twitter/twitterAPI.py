import json
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
import time
from pymongo import MongoClient
from requests.packages.urllib3.exceptions import ProtocolError

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


class listener(StreamListener, api=None):
    def __init__(self):
        super(listener, self).__init__()
        self.switch = True

    def on_status(self, data):
        try:
            if (self.switch == True):
                self.switch = False
                tweet = json.loads(data)
                with open('data.json', 'a') as outfile:
                     json.dump(tweet, outfile, sort_keys=True, indent=4)
                     outfile.write(',\n')
                tweets_test.insert_one(tweet)
                print tweet

                return True
            else:
                self.switch = True

        except BaseException, e:
            print 'failed ondata, ' , str(e)
            time.sleep(5)

    def on_error(self, status_code):
        print status_code

auth = OAuthHandler(ckey, csecret)
auth.set_access_token(atoken, asecret)

while True:
    try:
        twitterStream = Stream(auth, listener())
        twitterStream.filter(track=['2016 election', 'bernie sanders', 'hilary clinton', 'trump', 'ted cruz', 'democratic party', 'republican party'])
    except ProtocolError:
        time.sleep(5)
        continue
    except KeyboardInterrupt:
        twitterStream.disconnect()
        break