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
        port: 4200,
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
        },
        {
          test: /\.(png|jpe?g|svg|gif)$/,
          loader: 'file-loader',
          options: {
              name: '[path][name].[ext]',
          }

      }],
        
    },
    plugins: [
        new MiniCssExtractPlugin({
           filename: "style.css"
        }),
        new HTMLPlugin({
            filename: 'index.html',
            template: './src/html/index.html',
        }),
        new HTMLPlugin({
            filename: 'main.html',
            template: './src/html/main.html'
        }),
        new HTMLPlugin({
            filename: 'regpage.html',
            template: './src/html/regpage.html'
        }),
        new HTMLPlugin({
            filename: 'admin.html',
            template: './src/html/admin.html'
        }),
        new HTMLPlugin({
          filename: 'password.html',
          template: './src/html/password.html'
      }),
        new HTMLPlugin({
            filename: 'guest.html',
            template: './src/html/guest.html'
        }),
        new HTMLPlugin({
            filename: 'user.html',
            template: './src/html/user.html'
        }),
        new CopyPlugin({
            patterns: [{from:'src/img/', to: 'img'}]
        })
    ],
};