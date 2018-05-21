var path = require('path');
module.exports = {
    entry: './src/main/resources/static/js/app.js',
    devtool: 'cheap-module-eval-source-map',
    output: {
    	path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            },
            {
                test: /\.scss$/,
                use: ['style-loader', 'css-loader', 'sass-loader']
            }
        ]
    },
    devServer: {
        contentBase: path.join(__dirname, 'src', 'main', 'resources', 'templates')
    },
    mode: "development"
};

