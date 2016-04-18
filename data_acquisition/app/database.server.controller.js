/**
 * Created by samskim on 4/18/16.
 */

var fs = require('fs');

//var mongojs = require('mongojs');
//var db = mongojs('election-2016', ['tweets_test']);
//var collection = db.tweets_test;
var dumpfile = "../out/data.json";
var stream = fs.createWriteStream(dumpfile);


var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/election-2016';

stream.write('[');

var findTweets = function(db, total, callback){
    var cursor = db.collection('tweets_test').find( );
    var count = 0;

    cursor.each(function(err, doc){
        assert.equal(err, null);
        if (doc != null){


            stream.write(JSON.stringify(doc) + '\n');

            if (count != total - 1) stream.write(',');
            console.log('writing tweet: ' + doc.id);
            count++;

        }else {
            callback();
        }

    });
}

console.log('fetching data..');

MongoClient.connect(url, function(err, db){
    assert.equal(null, err);

    db.collection('tweets_test').count(function(err, count){

        findTweets(db, count, function(){
            db.close();
            stream.write(']');
        });
    });


});









// collection.find(function (err, docs){
// console.log('found ' + docs.length + ' files');
//
//     stream.on('error', function(err){ console.log(err) });
//

//     docs.forEach(function(doc, index, array){
//
//
//         stream.write(JSON.stringify(doc) + '\n');
//         if (index !== array.length - 1) stream.write(',');
//         console.log('writing tweet: ' + doc.id);
//     })
//     stream.write(']');
//     console.log('done.');
//     stream.end();
//     db.close();
// })