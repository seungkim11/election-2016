/**
 * Created by samskim on 4/18/16.
 */
var MongoClient = require('mongodb').MongoClient;

var fs = require('fs');

var assert = require('assert');
var url = 'mongodb://localhost:27017/election-2016';
var collectionName = 'tweets_test';
var tweetLimit = 30000;

var bulkwriteToFile = function(fileCount, array){

    var dumpfile = '../out/' + fileCount + '.json';
    var stream = fs.createWriteStream(dumpfile);
    stream.write('[');

    array.forEach(function(doc, index){
        if (index !== array.length - 1) stream.write(doc + ',' + '\n');
        else {
            stream.write(doc + '\n' + ']');
            stream.end();
            console.log('data written to ' + dumpfile);
        }
    })
}

var findTweets = function(db, total, callback){
    var cursor = db.collection(collectionName).find( );
    var fileCount = 1;
    var i = 0;
    var tweets = [];

    cursor.each(function(err, doc){
        assert.equal(err, null);
        if (doc != null){

            tweets.push(JSON.stringify(doc));
            i++;

            if (i >= tweetLimit){

                bulkwriteToFile(fileCount, tweets);
                fileCount++;
                i = 0;
                tweets = [];
            }

        }else {
            if (tweets){
                bulkwriteToFile(fileCount, tweets);
            }
            callback();
        }

    });
}


console.log('fetching data..');

MongoClient.connect(url, function(err, db){
    assert.equal(null, err);

    db.collection(collectionName).count(function(err, count){

        findTweets(db, count, function(){
            db.close();
            console.log('done');
        });
    });
});