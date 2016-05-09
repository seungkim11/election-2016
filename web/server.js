/**
 * Created by samskim on 4/18/16.
 */
'use strict';

var express = require('./express');

var app = express();

app.listen(3000);

console.log('Server running at http://localhost:3000/');

module.exports = app;