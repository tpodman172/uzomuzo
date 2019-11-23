var path = require('path');

module.exports = {
    mode: "development",
    entry: "./src/main.tsx",
    devtool: 'inline-source-map',
    devServer: {},
	output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "main.js"
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: "ts-loader"
            }
        ]
    },
    resolve: {
        extensions: [".ts", ".tsx", ".js", ".json"]
    }
};
