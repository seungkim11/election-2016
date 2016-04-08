import json
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
import time

with open('auth.json') as auth_file:
    auth_data = json.load(auth_file)

ckey = auth_data['ckey']
csecret = auth_data['csecret']
atoken = auth_data['atoken']
asecret = auth_data['asecret']

class listener(StreamListener):
    def on_data(self, data):
        try:
            print data
            dbfile = open('tweetDB.json', 'a')
            dbfile.write(data)
            dbfile.write('\n')
            dbfile.close()
            return True
        except BaseException, e:
            print 'failed ondata, ' , str(e)
            time.sleep(5)

    def on_error(self, status_code):
        print status_code

auth = OAuthHandler(ckey, csecret)
auth.set_access_token(atoken, asecret)

twitterStream = Stream(auth, listener())
twitterStream.filter(track=['election2016', 'debate', 'bernie sanders', 'hilary clinton', 'trump', 'ted cruz'])

