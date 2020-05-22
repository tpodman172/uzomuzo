import * as request from 'request';

exports.handler = (event: any, context: any, callback: Function) => {
    const url: string = 'http://weather.livedoor.com/forecast/webservice/json/v1?city=400040';
    request(url, (err: Error, res: request.RequestResponse, body: any) => {
        // Return if Error
        if(err || res.statusCode !== 200) { return callback(err); }

        // Parse JSON file (body)
        let jsonFile: any = JSON.parse(body);
        console.log(jsonFile.title);
        console.log(jsonFile.description);

        return callback(null, 'Success!');
    });
}