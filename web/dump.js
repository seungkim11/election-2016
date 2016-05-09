/**
 * Created by samskim on 4/19/16.
 */
var fs = require('fs');

var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://localhost:27017/election-2016';
var dumpfile = './out/data.json';
var stream = fs.createWriteStream(dumpfile);

var size = 50000;

var findTweets = function(db, total, callback){
    var i;
    var count = 0;
    stream.write('[');

    console.log('total: ' + total);
    for (i = 0; i < (total / size); i++){

        var recToSkip = i*size;

        db.collection('tweets_test').find().skip(recToSkip).limit(size).forEach( function( record ){

            if (count != total - 1){
                stream.write(JSON.stringify(record) + ',' + '\n');
                console.log('file finished writing' + count);
            }else{
                stream.write(JSON.stringify(record) + '\n' + ']');
                stream.end();
                console.log('ended');
                callback();
            }

            count++;

        })

    }
}

console.log('fetching data..');

MongoClient.connect(url, function(err, db){
    assert.equal(null, err);

    db.collection('tweets_test').count(function(err, count){

        findTweets(db, count, function(){
            db.close();
        });
    });


});