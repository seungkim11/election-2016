/**
 * Created by samskim on 4/18/16.
 */

var fs = require('fs');

var mongojs = require('mongojs');
var db = mongojs('election-2016', ['tweets_test']);
var collection = db.tweets_test;
var dumpfile = "../out/data.json";
var stream = fs.createWriteStream(dumpfile);

console.log('starting');


collection.find(function (err, docs){
console.log('found ' + docs.length + ' files');

    stream.on('error', function(err){ console.log(err) });

    stream.write('[');
    docs.forEach(function(doc, index, array){


        stream.write(JSON.stringify(doc) + '\n');
        if (index !== array.length - 1) stream.write(',');
        console.log('writing tweet: ' + doc.id);
    })
    stream.write(']');
    console.log('done.');
    stream.end();
    db.close();
})