const path = require('path');
const webpack = require('webpack');

module.exports = {
    mode: "development",
    entry: {
        app: ["./src/main.tsx"]
    },
    devtool: 'inline-source-map',
    devServer: {
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "main.js",
        publicPath: "/dist/",
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
