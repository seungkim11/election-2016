/**
 * Created by samskim on 4/18/16.
 */

var database = require('./database.server.controller');

module.exports = function(app){
    app.route('/api/download/:date').get(database.download);

}