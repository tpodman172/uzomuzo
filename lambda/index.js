"use strict";
exports.__esModule = true;
var request = require("request");
exports.handler = function (event, context, callback) {
    var url = 'http://weather.livedoor.com/forecast/webservice/json/v1?city=400040';
    request(url, function (err, res, body) {
        // Return if Error
        if (err || res.statusCode !== 200) {
            return callback(err);
        }
        // Parse JSON file (body)
        var jsonFile = JSON.parse(body);
        console.log(jsonFile.title);
        console.log(jsonFile.description);
        return callback(null, 'Success!');
    });
};
