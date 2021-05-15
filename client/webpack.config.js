let path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const HTMLPlugin = require("html-webpack-plugin");
const CopyPlugin = require("copy-webpack-plugin");

module.exports = {
    mode: 'development',
    entry:  './src/js/script.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist/'),
    },
    devServer: {
        contentBase: path.resolve(__dirname, '/dist')
    },
    //watch: true,
    devtool: 'source-map',
    module: {
        rules: [{
            test: /\.scss$/,
            use: [
                MiniCssExtractPlugin.loader,
                "css-loader", "sass-loader"
            ]
        }]
    },
    plugins: [
        new MiniCssExtractPlugin({
           filename: "style.css"
        }),
        new HTMLPlugin({
            filename: 'index.html',
            template: './src/index.html',
        }),
        new HTMLPlugin({
            filename: 'main.html',
            template: './src/main.html'
        }),
        new HTMLPlugin({
            filename: 'regpage.html',
            template: './src/regpage.html'
        }),
        new CopyPlugin({
            patterns: [{from:'src/img/', to: 'img'}]
        })
    ],
};