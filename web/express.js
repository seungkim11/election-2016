/**
 * Created by samskim on 4/18/16.
 */
'use strict';

var express = require('express'),
    morgan = require('morgan'),
    bodyParser = require('body-parser');

module.exports = function(){
    var app = express();

    app.use(morgan('dev'));

    app.use(bodyParser.urlencoded({
        extended: true
    }));

    app.use(bodyParser.json());

    require('./app/database.server.routes')(app);

    app.use(express.static('./public'));

    return app;
}