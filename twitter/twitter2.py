from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
import sys
import tweepy
import time
# import pydoop.hdfs as hdfs

#insert your API key
ckey = 'LWtf3j4bhoKdBLpWL8h3RnCXY'
#insert your API secret
csecret = '2NuowYQbatmw5CYuyXFLMZdt2nD5rsFQgC5hJGmBQd7cTTLPIj'
#insert your access token
atoken = '4264966752-8TjClsbwMPiEk2oUB2RtNyz7p2gwtOgPKLhQXHp'
#insert your access token secret
asecret = 'WdY8ftH1CLX3VxCFWm4fXCUPdHCpVm1a0NfXglYg09ihb'

auth = OAuthHandler(ckey, csecret)
auth.set_access_token(atoken, asecret)
api = tweepy.API(auth)

class listener(StreamListener):

    def on_data(self, data):
        try:
            with open ('data1.json','a') as f:
                f.write(data)
                f.close()
            return True
        except BaseException, e:
            print 'Failed ondata,',str(e)
            #time.sleep(5)

    def on_error(self, status):
        print status

    def on_timeout(self):
        print sys.stderr, 'Timeout...'
        return True # Don't kill the stream

twitterStream = Stream(auth, listener())
twitterStream.filter(track=['iphone6'])
